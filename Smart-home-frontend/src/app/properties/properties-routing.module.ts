import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PropertiesComponent } from './components/properties/properties.component';
import { PropertyProfileComponent } from './components/properties/property-profile/property-profile.component';
import { PropertyProfileFormComponent } from './components/properties/property-profile-form/property-profile-form.component';
import {
  PropertyProfileDevicesComponent
} from "./components/properties/property-profile/property-profile-devices/property-profile-devices.component";

const routes: Routes = [
  {
    path: '',
    component: PropertiesComponent,
    children: [
      {
        path: 'property/:id',
        component: PropertyProfileComponent,
      },
      {
        path: 'new/:id',
        component: PropertyProfileFormComponent,
      },
      {
        path: 'edit/:id',
        component: PropertyProfileFormComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PropertiesRoutingModule {}
