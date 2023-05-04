import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminGuard } from './auth/admin.guard';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: HomePageComponent,
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then((module) => module.AuthModule),
  },
  {
    path: 'security',
    loadChildren: () =>
      import('./certificates/certificates.module').then(
        (module) => module.CertificatesModule
      ),
    canActivate: [AuthGuard],
  },
  {
    path: 'admin/security',
    loadChildren: () =>
      import('./certificates-admin/certificates-admin.module').then(
        (module) => module.CertificatesAdminModule
      ),
    canActivate: [AuthGuard, AdminGuard],
  },
  {
    path: 'admin/users',
    loadChildren: () =>
      import('./users/users.module').then((module) => module.UsersModule),
    canActivate: [AuthGuard, AdminGuard],
  },
  {
    path: 'admin/properties',
    loadChildren: () =>
      import('./properties/properties.module').then(
        (module) => module.PropertiesModule
      ),
    canActivate: [AuthGuard, AdminGuard],
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
