import { NgModule } from '@angular/core';

import { UserPropertiesRoutingModule } from './user-properties-routing.module';
import { UserPropertiesComponent } from './components/user-properties/user-properties.component';
import { AllUserPropertiesComponent } from './components/user-properties/all-user-properties/all-user-properties.component';
import { UserPropertyProfileComponent } from './components/user-properties/user-property-profile/user-property-profile.component';
import { StoreModule } from '@ngrx/store';
import { SharedModule } from '../shared/shared.module';
import * as fromUserProperties from './store/user-properties.reducer';
import { EffectsModule } from '@ngrx/effects';
import { UserPropertiesEffects } from './store/user-properties.effects';

@NgModule({
  declarations: [
    UserPropertiesComponent,
    AllUserPropertiesComponent,
    UserPropertyProfileComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('user_properties', fromUserProperties.reducer),
    EffectsModule.forFeature([UserPropertiesEffects]),
    UserPropertiesRoutingModule,
  ],
})
export class UserPropertiesModule {}
