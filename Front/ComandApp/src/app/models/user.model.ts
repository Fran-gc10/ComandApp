export default class User {
  public constructor(
    public nombreUsuario: string,
    public email: string,
    public nombre: string,
    public password: string,
    public roles?: string[]
  ) {}
}
