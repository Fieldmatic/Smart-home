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
import { EffectsModule } from '@ngrx/effects';
import { PropertiesEffects } from './store/properties.effects';

@NgModule({
  declarations: [
    PropertiesComponent,
    PropertyProfileComponent,
    PropertyProfileFormComponent,
    PropertyProfileMembersComponent,
    PropertyMemberCardComponent,
    PropertyProfileDevicesComponent,
    AddPropertyDialogComponent,
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('properties', fromProperties.reducer),
    EffectsModule.forFeature([PropertiesEffects]),
    PropertiesRoutingModule,
  ],
})
export class PropertiesModule {}
