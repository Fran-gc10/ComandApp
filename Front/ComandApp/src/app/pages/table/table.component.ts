import { Component, OnInit, Signal, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, filter, map, mergeMap, tap } from 'rxjs';
import Product from 'src/app/models/product.model';
import TableProduct from 'src/app/models/table-product.model';
import { AlertService } from 'src/app/services/alert/alert.service';
import { ProductService } from 'src/app/services/product/product.service';
import { TableService } from 'src/app/services/table/table.service';
import { SweetAlertResult } from 'sweetalert2';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {
  viewType: 'products' | 'product-types' = 'product-types';
  commandedProducts: Product[] = [];
  tableNumber!: string;
  tableId!: number;
  productTypes$!: Observable<string[]>;
  products$!: Observable<Product[]>;
  productsToCommand: Product[] = [];

  public constructor(
    private route: ActivatedRoute,
    private tableService: TableService,
    private productService: ProductService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.route.params
      .pipe(filter((params) => params['id'] !== undefined))
      .subscribe((params) => {
        const id = params['id'];
        this.tableService
          .getTable(id)
          .pipe(
            tap((table) => {
              this.tableNumber = table.number;
              this.tableId = table.id;
              this.commandedProducts = table.products;
            })
          )
          .subscribe();

        this.productTypes$ = this.productService.getProductTypes();
      });
  }

  selectProductCategory(type: string) {
    if (this.viewType === 'product-types') {
      this.viewType = 'products';
      this.products$ = this.productService.getProductsByType(type);
    }
  }

  formatCurrency(value: number): string {
    return value.toLocaleString('es-ES', {
      style: 'currency',
      currency: 'EUR',
    });
  }

  addProductToCommands(product: Product) {
    this.productsToCommand.push(product);
  }

  deleteProduct(i: number) {
    this.productsToCommand.splice(i, 1);
  }

  chargeTable() {
    const productPrices = this.commandedProducts.map((pro) => pro.price);
    const totalToPay = productPrices
      .reduce((accumulator, currentValue) => accumulator + currentValue, 0)
      .toFixed(2);

    this.alertService
      .custom({
        title: `Cobrar Mesa Nº${this.tableNumber}`,
        text: `El total a cobrar es ${totalToPay}€`,
        icon: 'warning',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Cobrar',
        reverseButtons: true,
        customClass: {
          confirmButton: 'btn-charge',
        },
      })
      .pipe(
        mergeMap((result: SweetAlertResult) => {
          if (result.isConfirmed) {
            return this.tableService.chargeTable(this.tableId).pipe(
              tap((table) => {
                this.tableNumber = table.number;
                this.productsToCommand = [];
                this.commandedProducts = table.products;
              })
            );
          }
          return this.alertService.error('Cancelado', 'No se ha cobrao na :)');
        })
      )
      .subscribe();
  }

  command() {
    this.tableService
      .commandTable(this.tableId, this.productsToCommand)
      .pipe(
        tap((table) => {
          this.tableNumber = table.number;
          this.productsToCommand = [];
          this.commandedProducts = table.products;
        })
      )
      .subscribe();
  }
}
