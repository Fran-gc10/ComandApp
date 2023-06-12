import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import ProductByTypeResponse from 'src/app/models/product-by-type-response.interface';
import ProductTypesResponse from 'src/app/models/product-types-response.interface';
import Product from 'src/app/models/product.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private path = environment.host + '/Producto';

  constructor(private http: HttpClient) {}

  getProductTypes(): Observable<string[]> {
    return this.http
      .get<ProductTypesResponse>(this.path + '/Tipos')
      .pipe(map((res: ProductTypesResponse) => res.data));
  }

  getProductsByType(type: string): Observable<Product[]> {
    return this.http
      .get<ProductByTypeResponse>(`${this.path}/ProdByTipo/${type}`)
      .pipe(
        map((res: ProductByTypeResponse) =>
          Product.transformProductosResponse(res.data)
        )
      );
  }
}
