import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { StoreModule } from '@ngrx/store';
import * as fromUsers from './store/users.reducer';
import { UsersComponent } from './components/users/users.component';
import { UsersRoutingModule } from './users-routing.module';
import { AllUsersTableComponent } from './components/users/all-users-table/all-users-table.component';

@NgModule({
  declarations: [UsersComponent, AllUsersTableComponent],
  imports: [
    SharedModule,
    StoreModule.forFeature('users', fromUsers.reducer),
    UsersRoutingModule,
  ],
})
export class UsersModule {}
