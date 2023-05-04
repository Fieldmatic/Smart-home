import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './components/users/users.component';
import { AllUsersTableComponent } from './components/users/all-users-table/all-users-table.component';
import { UsersResolver } from './resolvers/users.resolver';
import { UserProfileComponent } from './components/users/user-profile/user-profile.component';
import { UserProfileFormComponent } from './components/users/user-profile-form/user-profile-form.component';
import { UserPropertiesResolver } from './resolvers/user-properties.resolver';

const routes: Routes = [
  {
    path: '',
    component: UsersComponent,
    children: [
      {
        path: 'all',
        component: AllUsersTableComponent,
        resolve: [UsersResolver],
      },
      {
        path: 'new',
        component: UserProfileFormComponent,
      },
      {
        path: 'user/:id',
        component: UserProfileComponent,
        resolve: [UserPropertiesResolver],
      },
      {
        path: 'edit/:id',
        component: UserProfileFormComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsersRoutingModule {}
