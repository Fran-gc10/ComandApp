import ApiResponse from './api-response.interface';
import Mesa from './mesa.interface';

export default interface TablesResponse extends ApiResponse<Mesa[]> {}
