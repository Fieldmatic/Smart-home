import { NgModule } from '@angular/core';

import { PropertiesRoutingModule } from './properties-routing.module';
import { PropertiesComponent } from './components/properties/properties.component';
import { PropertyProfileComponent } from './components/properties/property-profile/property-profile.component';
import { StoreModule } from '@ngrx/store';
import * as fromProperties from './store/properties.reducer';
import { SharedModule } from '../shared/shared.module';
import { PropertyProfileFormComponent } from './components/properties/property-profile-form/property-profile-form.component';
import { PropertyProfileMembersComponent } from './components/properties/property-profile/property-profile-members/property-profile-members.component';
import { PropertyMemberCardComponent } from './components/properties/property-profile/property-profile-members/property-member-card/property-member-card.component';
import { PropertyProfileDevicesComponent } from './components/properties/property-profile/property-profile-devices/property-profile-devices.component';
import { AddPropertyDialogComponent } from './components/properties/property-profile/property-profile-devices/add-property-device-dialog/add-property-dialog.component';
import { PropertyProfileLogsComponent } from './components/properties/property-profile/property-profile-logs/property-profile-logs.component';
import { EffectsModule } from '@ngrx/effects';
import { PropertiesEffects } from './store/properties.effects';
import { PropertyProfileAlarmsComponent } from './components/properties/property-profile/property-profile-alarms/property-profile-alarms.component';
import { PropertyAlarmsCardComponent } from './components/properties/property-profile/property-profile-alarms/property-alarms-card/property-alarms-card.component';
import { AddDeviceRuleDialogComponent } from './components/properties/property-profile/property-profile-devices/add-device-rule-dialog/add-device-rule-dialog.component';

@NgModule({
  declarations: [
    PropertiesComponent,
    PropertyProfileComponent,
    PropertyProfileFormComponent,
    PropertyProfileMembersComponent,
    PropertyMemberCardComponent,
    PropertyProfileDevicesComponent,
    AddPropertyDialogComponent,
    PropertyProfileLogsComponent,
    PropertyProfileAlarmsComponent,
    PropertyAlarmsCardComponent,
    AddDeviceRuleDialogComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('properties', fromProperties.reducer),
    EffectsModule.forFeature([PropertiesEffects]),
    PropertiesRoutingModule,
  ],
})
export class PropertiesModule {}
