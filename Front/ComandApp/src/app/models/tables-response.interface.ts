import Mesa from './mesa.interface';

export default interface TablesResponseInterface {
  code: string;
  data: Mesa[];
  message: string;
  status: string;
}
