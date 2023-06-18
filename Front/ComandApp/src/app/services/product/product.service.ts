import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import Product from 'src/app/models/product.model';
import EmptyResponse from 'src/app/models/responses/empty-response.interface';
import ApiResponse from 'src/app/models/responses/api-response.interface';
import ProductTypesResponse from 'src/app/models/responses/product-types-response.interface';
import ProductoResponse from 'src/app/models/responses/producto-response.interface';
import ProductosResponse from 'src/app/models/responses/productos-response.interface';
import { environment } from 'src/environments/environment';
import { AlertService } from '../alert/alert.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private path = environment.host + '/Producto';

  constructor(private http: HttpClient, private alertService: AlertService) {}

  getProductTypes(): Observable<string[]> {
    return this.http
      .get<ProductTypesResponse>(this.path + '/Tipos')
      .pipe(map((res) => res.data));
  }

  getProductsByType(type: string): Observable<Product[]> {
    return this.http
      .get<ProductosResponse>(`${this.path}/ProdByTipo/${type}`)
      .pipe(map((res) => Product.transformProductosResponse(res.data)));
  }

  getAll(): Observable<Product[]> {
    return this.http
      .get<ProductosResponse>(`${this.path}`)
      .pipe(map((res) => Product.transformProductosResponse(res.data)));
  }

  get(productId: number): Observable<Product> {
    return this.http
      .get<ProductoResponse>(`${this.path}/${productId}`)
      .pipe(map((res) => Product.transformProductoToProduct(res.data)));
  }

  create(product: Product): Observable<Product> {
    const producto = Product.transformProductToProducto(product);
    return this.http.post<ProductoResponse>(`${this.path}`, producto).pipe(
      catchError((error: HttpErrorResponse) => {
        this.alertService.error(error.error.message);
        return throwError(() => new Error(error.error.message));
      }),
      map((res) => Product.transformProductoToProduct(res.data))
    );
  }

  update(product: Product): Observable<Product> {
    const producto = Product.transformProductToProducto(product);
    return this.http.put<ProductoResponse>(`${this.path}`, producto).pipe(
      catchError((error: HttpErrorResponse) => {
        this.alertService.error(error.error.message);
        return throwError(() => new Error(error.error.message));
      }),
      map((res) => Product.transformProductoToProduct(res.data))
    );
  }

  delete(productId: number): Observable<boolean> {
    return this.http
      .delete<ApiResponse<ProductoResponse>>(`${this.path}/${productId}`)
      .pipe(map((res) => res.code === '200 OK'));
  }
}
