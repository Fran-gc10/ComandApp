import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(
    req: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const authToken = this.authService.getToken();

    if (this.requiresAuthorization(req.url)) {
      req = req.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          Authorization: `Bearer ${authToken}`,
        },
      });
    }

    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {
        return throwError(err);
      })
    );
  }

  private requiresAuthorization(url: string): boolean {
    const unrestrictedUrls = ['auth/nuevoUsuario', 'auth/login'];

    for (const unrestrictedUrl of unrestrictedUrls) {
      if (url.includes(unrestrictedUrl)) return false;
    }

    return true;
  }
}
