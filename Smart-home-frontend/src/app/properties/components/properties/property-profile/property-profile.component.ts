import { Component, OnDestroy, OnInit } from '@angular/core';
import { Property } from '../../../../shared/model/property';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectPropertyWithId } from '../../../store/properties.selectors';
import { selectRole } from '../../../../auth/store/auth.selectors';

@Component({
  selector: 'app-property-profile',
  templateUrl: './property-profile.component.html',
  styleUrls: ['./property-profile.component.scss'],
})
export class PropertyProfileComponent implements OnInit, OnDestroy {
  property!: Property;
  propertyStoreSubscription!: Subscription;
  authStoreSubscription!: Subscription;
  loggedInUsersRole: string | null | undefined;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.propertyStoreSubscription = this.store
      .select(selectPropertyWithId(id))
      .subscribe((property) => {
        this.property = property;
        console.log("Properti " + this.property.devices[0].name)
      });
    this.authStoreSubscription = this.store
      .select(selectRole)
      .subscribe((role) => {
        this.loggedInUsersRole = role;
      });
  }

  ngOnDestroy() {
    this.propertyStoreSubscription.unsubscribe();
    this.authStoreSubscription.unsubscribe();
  }
}
