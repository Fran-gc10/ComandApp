import Producto from "./responses/producto.interface";

export default class Product {
  public constructor(
    public id: number,
    public quantity: number,
    public name: string,
    public price: number,
    public type: 'BEBIDA' | 'COMIDA' | 'POSTRE'
  ) {}

  static transformProductosResponse(products: Producto[]): Product[] {
    return products.map((product) => this.transformProductoToProduct(product));
  }

  static transformProductoToProduct(producto: Producto): Product {
    return new Product(
      producto.id,
      producto.cantidad,
      producto.nombre,
      producto.precio,
      producto.tipo
    );
  }

  static transformProductsToProductos(products: Product[]): Producto[] {
    return products.map((product) => this.transformProductToProducto(product));
  }

  static transformProductToProducto(product: Product): Producto {
    return {
      cantidad: product.quantity,
      id: product.id,
      nombre: product.name,
      precio: product.price,
      tipo: product.type,
    };
  }
}
