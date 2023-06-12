import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import Table from 'src/app/models/table.model';
import { environment } from 'src/environments/environment';
import Product from 'src/app/models/product.model';
import TablesResponseInterface from '../../models/tables-response.interface';
import TableResponseInterface from 'src/app/models/table-response.interface';
import TableProduct from 'src/app/models/table-product.model';

@Injectable({
  providedIn: 'root',
})
export class TableService {
  private path = environment.host + '/Mesa';

  constructor(private http: HttpClient) {}

  public getTables(): Observable<Table[]> {
    return this.http.get<TablesResponseInterface>(this.path).pipe(
      map((res: TablesResponseInterface) => {
        const tables = res?.data?.map(
          (table) => new Table(table.id, table.numero)
        );
        return tables;
      })
    );
  }

  public getTable(id: number): Observable<TableProduct> {
    return this.http.get<TableResponseInterface>(`${this.path}/${id}`).pipe(
      map((res: TableResponseInterface) => {
        const table = res?.data;
        return new TableProduct(
          table.id,
          table.numero,
          Product.transformProductosResponse(table.productos)
        );
      })
    );
  }

  public commandTable(
    tableId: number,
    products: Product[]
  ): Observable<TableProduct> {
    return this.http
      .put<TableResponseInterface>(
        `${this.path}/Comandar/${tableId}`,
        Product.transformProductsToProductos(products)
      )
      .pipe(
        map((res: TableResponseInterface) => {
          const table = res?.data;
          return new TableProduct(
            table.id,
            table.numero,
            Product.transformProductosResponse(table.productos)
          );
        })
      );
  }

  public chargeTable(tableId: number) {
    return this.http
      .put<TableResponseInterface>(`${this.path}/CobrarMesa/${tableId}`, {})
      .pipe(
        map((res: TableResponseInterface) => {
          const table = res?.data;
          return new TableProduct(
            table.id,
            table.numero,
            Product.transformProductosResponse(table.productos)
          );
        })
      );
  }
}
