import { effect, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

export const loggedGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const authService: AuthService = inject(AuthService);
  effect(() => {
    if (!authService.logged()) {
      router.navigate(['/login']).catch((err) => console.log(err));
    }
  });
  return authService.logged();
};
