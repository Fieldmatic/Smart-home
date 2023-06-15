import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserPropertiesComponent } from './components/user-properties/user-properties.component';
import { AllUserPropertiesComponent } from './components/user-properties/all-user-properties/all-user-properties.component';
import { UserPropertyProfileComponent } from './components/user-properties/user-property-profile/user-property-profile.component';
import { AccessiblePropertiesResolver } from './resolvers/accessible-properties.resolver';
import { PropertyMessagesResolver } from './resolvers/property-messages.resolver';

const routes: Routes = [
  {
    path: '',
    component: UserPropertiesComponent,
    children: [
      {
        path: 'all',
        pathMatch: 'full',
        component: AllUserPropertiesComponent,
        resolve: [AccessiblePropertiesResolver],
      },
      {
        path: ':id',
        component: UserPropertyProfileComponent,
        resolve: [PropertyMessagesResolver],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserPropertiesRoutingModule {}
