import ApiResponse from './api-response.interface';
import Producto from './producto.interface';

export default interface ProductosResponse extends ApiResponse<Producto[]> {}
