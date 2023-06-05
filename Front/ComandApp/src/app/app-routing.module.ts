import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { RegistrarseComponent } from './pages/registrarse/registrarse.component';
import { loggedGuard } from './guards/logged/logged.guard';
import { checkLoginGuard } from './guards/check-login/check-login.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent, canActivate: [loggedGuard] },
  { path: 'login', component: LoginComponent, canActivate: [checkLoginGuard] },
  { path: 'registrarse', component: RegistrarseComponent, canActivate: [checkLoginGuard] },
  // { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
