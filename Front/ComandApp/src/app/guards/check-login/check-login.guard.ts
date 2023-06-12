import { computed, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

export const checkLoginGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const authService: AuthService = inject(AuthService);
  const isNotLogged = computed(() => {
    const logged = authService.logged();
    if (logged) router.navigate(['/mesas']).catch((err) => console.log(err));
    return !logged;
  });
  return isNotLogged();
};
