import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import LoginResponse from 'src/app/models/responses/login-response.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = environment.host + '/auth';
  public logged;

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwt: JwtHelperService
  ) {
    this.logged = signal(this.isLogged);
  }

  login(username: string, password: string): Observable<any> {
    return this.http
      .post<LoginResponse>(this.authUrl + '/login', {
        nombreUsuario: username,
        password,
      })
      .pipe(
        map((data: LoginResponse) => {
          const isAdmin = data.authorities.some(
            (authority) => authority.authority === 'ROLE_ADMIN'
          );
          if (isAdmin) this.setAdminUser();
          this.setToken(data.token);
          this.router.navigateByUrl('mesas').catch((err) => console.log(err));
          this.logged.set(true);
        })
      );
  }

  logout(): void {
    localStorage.clear();
    this.logged.set(false);
    this.router.navigateByUrl('login').catch((err) => console.log(err));
  }

  private get isLogged(): boolean {
    const userToken = this.getToken();
    if (!userToken) return false;

    const isExpired = this.jwt.isTokenExpired(userToken);
    if (isExpired) this.logout();

    return !isExpired;
  }

  getLoggedUserId(): number | undefined {
    const token = this.getToken();
    if (!token) return undefined;
    const decodedToken = this.jwt.decodeToken(token);
    if (!decodedToken) return undefined;
    return decodedToken.userId;
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private setAdminUser() {
    localStorage.setItem('isUserAnAdmin', 'true');
  }

  isAdmin() {
    return localStorage.getItem('isUserAnAdmin') === 'true';
  }
}
