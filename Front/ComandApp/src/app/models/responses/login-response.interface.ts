
export default interface LoginResponse {
  token: string;
  bearer: string;
  nombreUsuario: string;
  authorities: Authority[];
}

interface Authority {
  authority: "ROLE_USER" | "ROLE_ADMIN";
}