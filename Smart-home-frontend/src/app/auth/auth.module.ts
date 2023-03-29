import { NgModule } from '@angular/core';
import { LoginFormComponent } from './components/auth/login-form/login-form.component';
import { SignupFormComponent } from './components/auth/signup-form/signup-form.component';
import { AuthComponent } from './components/auth/auth.component';
import { SharedModule } from '../shared/shared.module';
import { AuthRoutingModule } from './auth-routing.module';
import { ForgotPasswordFormComponent } from './components/auth/forgot-password-form/forgot-password-form.component';

@NgModule({
  declarations: [
    LoginFormComponent,
    SignupFormComponent,
    AuthComponent,
    ForgotPasswordFormComponent,
  ],
  imports: [AuthRoutingModule, SharedModule],
})
export class AuthModule {}
