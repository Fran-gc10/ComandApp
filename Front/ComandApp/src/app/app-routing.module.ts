import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { RegistrarseComponent } from './pages/registrarse/registrarse.component';
import { loggedGuard } from './guards/logged/logged.guard';
import { checkLoginGuard } from './guards/check-login/check-login.guard';
import { TablesComponent } from './pages/tables/tables.component';
import { TableComponent } from './pages/table/table.component';
import { AdminComponent } from './pages/admin/admin.component';
import { adminGuard } from './guards/admin/admin.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/mesas' },
  { path: 'home', component: HomeComponent },
  { path: 'mesas', component: TablesComponent, canActivate: [loggedGuard] },
  { path: 'mesa/:id', component: TableComponent, canActivate: [loggedGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [loggedGuard, adminGuard] },
  { path: 'login', component: LoginComponent, canActivate: [checkLoginGuard] },
  { path: 'registrarse', component: RegistrarseComponent, canActivate: [checkLoginGuard] },
  // { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
