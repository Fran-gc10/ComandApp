export default class User {
  public constructor(
    public username: string,
    public email: string,
    public name: string,
    public password: string,
    public roles: string[]
  ) {}
}
