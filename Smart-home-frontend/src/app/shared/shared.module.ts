import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormNavigationComponent } from './components/form-navigation/form-navigation.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogWithReasonOptionsComponent } from './components/confirmation-dialog-with-reason-options/confirmation-dialog-with-reason-options.component';
import { PropertyProfileInfoComponent } from './components/property-profile-info/property-profile-info.component';
import { RouterModule } from '@angular/router';
import { PropertyDevicesCardComponent } from './components/property-devices-card/property-devices-card.component';
import {PropertyAlarmsCardComponent} from "./components/property-alarms-card/property-alarms-card.component";

@NgModule({
  imports: [CommonModule, ReactiveFormsModule, MaterialModule, RouterModule],
  exports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FormNavigationComponent,
    PropertyProfileInfoComponent,
    PropertyDevicesCardComponent,
    PropertyAlarmsCardComponent,
  ],
  declarations: [
    FormNavigationComponent,
    ConfirmationDialogComponent,
    ConfirmationDialogWithReasonOptionsComponent,
    PropertyProfileInfoComponent,
    PropertyDevicesCardComponent,
    PropertyAlarmsCardComponent,
  ],
})
export class SharedModule {}
