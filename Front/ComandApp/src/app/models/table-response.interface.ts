import MesaProductos from './mesa-productos.interface';

export default interface TableResponseInterface {
  code: string;
  data: MesaProductos;
  message: string;
  status: string;
}
