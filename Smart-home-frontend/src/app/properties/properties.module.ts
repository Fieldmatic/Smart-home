import { NgModule } from '@angular/core';

import { PropertiesRoutingModule } from './properties-routing.module';
import { PropertiesComponent } from './components/properties/properties.component';
import { PropertyProfileComponent } from './components/properties/property-profile/property-profile.component';
import { StoreModule } from '@ngrx/store';
import * as fromProperties from './store/properties.reducer';
import { SharedModule } from '../shared/shared.module';
import { PropertyProfileFormComponent } from './components/properties/property-profile-form/property-profile-form.component';
import { PropertyProfileInfoComponent } from './components/properties/property-profile/property-profile-info/property-profile-info.component';
import { PropertyProfileMembersComponent } from './components/properties/property-profile/property-profile-members/property-profile-members.component';
import { PropertyMemberCardComponent } from './components/properties/property-profile/property-profile-members/property-member-card/property-member-card.component';
import { ProperyProfileDevicesComponent } from './components/properties/property-profile/propery-profile-devices/propery-profile-devices.component';
import { PropertyDevicesCardComponent } from './components/properties/property-profile/property-devices-card/property-devices-card.component';

@NgModule({
  declarations: [
    PropertiesComponent,
    PropertyProfileComponent,
    PropertyProfileFormComponent,
    PropertyProfileInfoComponent,
    PropertyProfileMembersComponent,
    PropertyMemberCardComponent,
    ProperyProfileDevicesComponent,
    PropertyDevicesCardComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('properties', fromProperties.reducer),
    PropertiesRoutingModule,
  ],
})
export class PropertiesModule {}
