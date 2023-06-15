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
import { UserPropertyProfileDevicesComponent } from './components/user-properties/user-property-profile/user-property-profile-devices/user-property-profile-devices.component';
import { UserPropertyProfileMessagesComponent } from './components/user-properties/user-property-profile/user-property-profile-messages/user-property-profile-messages.component';

@NgModule({
  declarations: [
    UserPropertiesComponent,
    AllUserPropertiesComponent,
    UserPropertyProfileComponent,
    UserPropertyProfileDevicesComponent,
    UserPropertyProfileMessagesComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('user_properties', fromUserProperties.reducer),
    EffectsModule.forFeature([UserPropertiesEffects]),
    UserPropertiesRoutingModule,
  ],
})
export class UserPropertiesModule {}
