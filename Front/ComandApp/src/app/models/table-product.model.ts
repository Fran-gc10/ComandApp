import Product from './product.model';

export default class TableProduct {
  public constructor(
    public id: number,
    public number: string,
    public products: Product[]
  ) {}
}
