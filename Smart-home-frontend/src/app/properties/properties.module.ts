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
import { AddPropertyDialogComponent } from './components/properties/property-profile/property-profile-devices/add-property-device-dialog/add-property-dialog.component';
import { PropertyProfileLogsComponent } from './components/properties/property-profile/property-profile-logs/property-profile-logs.component';
import { PropertyProfileAlarmsComponent } from './components/properties/property-profile/property-profile-alarms/property-profile-alarms.component';
import { PropertyAlarmsCardComponent } from './components/properties/property-profile/property-profile-alarms/property-alarms-card/property-alarms-card.component';
import { AddDeviceRuleDialogComponent } from './components/properties/property-profile/property-profile-devices/add-device-rule-dialog/add-device-rule-dialog.component';

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
    PropertyProfileLogsComponent,
    PropertyProfileAlarmsComponent,
    PropertyAlarmsCardComponent,
    AddDeviceRuleDialogComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('properties', fromProperties.reducer),
    PropertiesRoutingModule,
  ],
})
export class PropertiesModule {}
