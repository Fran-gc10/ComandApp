import { Component, OnInit, Signal, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, filter, map, tap } from 'rxjs';
import Product from 'src/app/models/product.model';
import TableProduct from 'src/app/models/table-product.model';
import { ProductService } from 'src/app/services/product/product.service';
import { TableService } from 'src/app/services/table/table.service';

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
    private productService: ProductService
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
    this.tableService
      .chargeTable(this.tableId)
      .pipe(
        tap((table) => {
          this.tableNumber = table.number;
          this.productsToCommand = [];
          this.commandedProducts = table.products;
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
