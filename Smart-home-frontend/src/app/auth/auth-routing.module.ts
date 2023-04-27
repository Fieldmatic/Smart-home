import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './components/auth/auth.component';
import { LoginFormComponent } from './components/auth/login-form/login-form.component';
import { SignupFormComponent } from './components/auth/signup-form/signup-form.component';
import { ForgotPasswordFormComponent } from './components/auth/forgot-password-form/forgot-password-form.component';
import { EmailConfirmationComponent } from './components/auth/email-confirmation/email-confirmation.component';
import { LoginPinFormComponent } from './components/auth/login-pin-form/login-pin-form.component';
import { PinGuard } from './pin.guard';

const routes: Routes = [
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: 'login',
        component: LoginFormComponent,
      },
      {
        path: 'auth-confirm',
        component: LoginPinFormComponent,
        canActivate: [PinGuard],
      },
      {
        path: 'sign-up',
        component: SignupFormComponent,
      },
      {
        path: 'email-confirm/:token',
        component: EmailConfirmationComponent,
      },
      {
        path: 'reset-password',
        component: ForgotPasswordFormComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {}
