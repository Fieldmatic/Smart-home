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
import { PropertyProfileDevicesComponent } from './components/properties/property-profile/property-profile-devices/property-profile-devices.component';
import { PropertyDevicesCardComponent } from './components/properties/property-profile/property-profile-devices/property-devices-card/property-devices-card.component';
import { AddPropertyDialogComponent } from './components/properties/property-profile/property-profile-devices/add-property-dialog/add-property-dialog.component';

@NgModule({
  declarations: [
    PropertiesComponent,
    PropertyProfileComponent,
    PropertyProfileFormComponent,
    PropertyProfileInfoComponent,
    PropertyProfileMembersComponent,
    PropertyMemberCardComponent,
    PropertyProfileDevicesComponent,
    PropertyDevicesCardComponent,
    AddPropertyDialogComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('properties', fromProperties.reducer),
    PropertiesRoutingModule,
  ],
})
export class PropertiesModule {}
