import { Component, OnInit } from '@angular/core';
import Product from 'src/app/models/product.model';
import { AlertService } from 'src/app/services/alert/alert.service';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit {
  private _products: Product[] = [];
  productTypes: string[] = [];

  creatingProduct = false;
  newProduct?: Product;
  productEdit?: Product;

  public constructor(
    private productService: ProductService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.productService
      .getAll()
      .subscribe((products) => (this.products = products));
    this.productService
      .getProductTypes()
      .subscribe((types) => (this.productTypes = types));
  }

  get products() {
    return this._products;
  }

  set products(products: Product[]) {
    products.sort((a, b) => {
      if (a.type < b.type) {
        return -1;
      } else if (a.type > b.type) {
        return 1;
      } else {
        return 0;
      }
    });
    this._products = products;
  }

  deleteProduct(product: Product) {
    const id = product?.id;
    if (!this._products.some((product) => product.id === id)) return;

    this.productService.delete(id).subscribe((deleted) => {
      if (deleted)
        this._products = this._products.filter((product) => product.id !== id);
    });
  }

  onEditProductClick(product: Product) {
    this.productEdit = structuredClone(product);
  }

  isProductBeingEdited(product: Product) {
    return this.productEdit && product?.id === this.productEdit.id;
  }

  onSaveEditedProduct() {
    if (!this.productEdit || !this.isValidProduct(this.productEdit)) return;

    this.productService.update(this.productEdit).subscribe((updatedProduct) => {
      if (!updatedProduct) return;

      this._products = this._products.map((product) => {
        return product.id === updatedProduct?.id ? updatedProduct : product;
      });

      this.productEdit = undefined;
    });
  }

  onCancelEditingProduct() {
    console.log("product", this.productEdit)
    this.productEdit = undefined;
  }

  onAddNewProduct() {
    this.newProduct = new Product(0, 0, '', 0, 'COMIDA');
  }

  onSaveNewProduct() {
    if (!this.newProduct || !this.isValidProduct(this.newProduct)) {
      return;
    }

    this.productService.create(this.newProduct).subscribe((createdProduct) => {
      if (!createdProduct) return;
      this._products.unshift(createdProduct);
      this.newProduct = undefined;
    });
  }
  
  onCancelNewProduct() {
    this.newProduct = undefined;
  }

  private isValidProduct(product?: Product) {
    if (!product?.type || !product?.name || product?.price < 0) {
      this.alertService.error(
        'Por favor, introduce un producto correcto',
        'Error al guardar el producto'
      );
      return false;
    }

    return true;
  }
}
