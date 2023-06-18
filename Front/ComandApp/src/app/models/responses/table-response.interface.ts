import ApiResponse from './api-response.interface';
import MesaProductos from './mesa-productos.interface';

export default interface TableResponse
  extends ApiResponse<MesaProductos> {}
