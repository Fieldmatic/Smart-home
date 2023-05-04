import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../../model/user.model';
import { Store } from '@ngrx/store';
import { selectUserById } from '../../../store/users.selectors';
import { Subscription } from 'rxjs';
import { Property } from '../../../../shared/model/property';
import { selectRole } from '../../../../auth/store/auth.selectors';
import { selectUserProperties } from '../../../../properties/store/properties.selectors';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit, OnDestroy {
  user!: User;
  properties: Property[] = [];
  userStoreSubscription!: Subscription;
  propertiesStoreSubscription!: Subscription;
  authStoreSubscription!: Subscription;
  loggedInUsersRole: string | null | undefined;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.userStoreSubscription = this.store
      .select(selectUserById(id))
      .subscribe((user) => {
        this.user = user;
      });
    this.propertiesStoreSubscription = this.store
      .select(selectUserProperties)
      .subscribe((properties) => {
        this.properties = properties;
      });
    this.authStoreSubscription = this.store
      .select(selectRole)
      .subscribe((role) => {
        this.loggedInUsersRole = role;
      });
  }

  ngOnDestroy() {
    this.propertiesStoreSubscription.unsubscribe();
    this.userStoreSubscription.unsubscribe();
    this.authStoreSubscription.unsubscribe();
  }
}
