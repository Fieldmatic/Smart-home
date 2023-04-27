import { NgModule } from '@angular/core';
import { LoginFormComponent } from './components/auth/login-form/login-form.component';
import { SignupFormComponent } from './components/auth/signup-form/signup-form.component';
import { AuthComponent } from './components/auth/auth.component';
import { SharedModule } from '../shared/shared.module';
import { AuthRoutingModule } from './auth-routing.module';
import { ForgotPasswordFormComponent } from './components/auth/forgot-password-form/forgot-password-form.component';
import { StoreModule } from '@ngrx/store';
import * as fromAuth from './store/auth.reducer';
import { EmailConfirmationComponent } from './components/auth/email-confirmation/email-confirmation.component';
import { LoginPinFormComponent } from './components/auth/login-pin-form/login-pin-form.component';
import { TimerComponent } from './components/auth/login-pin-form/timer/timer.component';

@NgModule({
  declarations: [
    LoginFormComponent,
    SignupFormComponent,
    AuthComponent,
    ForgotPasswordFormComponent,
    EmailConfirmationComponent,
    LoginPinFormComponent,
    TimerComponent,
  ],
  imports: [
    AuthRoutingModule,
    SharedModule,
    StoreModule.forFeature('auth', fromAuth.reducer),
  ],
})
export class AuthModule {}
