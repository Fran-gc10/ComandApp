import Producto from './producto.interface';

export default interface MesaProductos {
  id: number;
  numero: string;
  productos: Producto[];
}
