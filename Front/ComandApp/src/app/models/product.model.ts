import Producto from './producto.interface';

export default class Product {
  public constructor(
    public id: number,
    public quantity: number,
    public name: string,
    public price: number,
    public type: 'BEBIDA' | 'COMIDA' | 'POSTRE'
  ) {}

  static transformProductosResponse(products: Producto[]): Product[] {
    return products.map(
      (product) =>
        new Product(
          product.id,
          product.cantidad,
          product.nombre,
          product.precio,
          product.tipo
        )
    );
  }

  static transformProductsToProductos(products: Product[]): Producto[] {
    return products.map((product) => {
      return {
        cantidad: product.quantity,
        id: product.id,
        nombre: product.name,
        precio: product.price,
        tipo: product.type,
      };
    });
  }
}
