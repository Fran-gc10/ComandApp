import Producto from './producto.interface';

export default interface ProductByTypeResponse {
  code: string;
  data: Producto[];
  message: string;
  status: string;
}
