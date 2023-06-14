import { NgModule } from '@angular/core';

import { PasswordRoutingModule } from './password-routing.module';
import { SharedModule } from '../shared/shared.module';
import { PasswordComponent } from './components/password/password.component';
import { SetPasswordFormComponent } from './components/password/set-password-form/set-password-form.component';
import { ForgotPasswordFormComponent } from './components/password/forgot-password-form/forgot-password-form.component';
import { EffectsModule } from '@ngrx/effects';
import { PasswordEffects } from './store/password.effects';

@NgModule({
  declarations: [
    PasswordComponent,
    SetPasswordFormComponent,
    ForgotPasswordFormComponent,
  ],
  imports: [
    SharedModule,
    PasswordRoutingModule,
    EffectsModule.forFeature([PasswordEffects]),
  ],
})
export class PasswordModule {}
