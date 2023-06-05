import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import User from 'src/app/models/user.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public createUser(user: User) {
    return this.http.post(`${environment.host}/auth/nuevoUsuario`, user);
  }
}
