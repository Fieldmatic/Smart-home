import { Component, OnDestroy, OnInit } from '@angular/core';
import { Property } from '../../../../shared/model/property';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectAccessiblePropertyById } from '../../../store/user-properties.selectors';

@Component({
  selector: 'app-user-property-profile',
  templateUrl: './user-property-profile.component.html',
  styleUrls: ['./user-property-profile.component.scss'],
})
export class UserPropertyProfileComponent implements OnInit, OnDestroy {
  property!: Property;
  private storeSubscription!: Subscription;
  private routeSubscription!: Subscription;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit() {
    this.routeSubscription = this.route.params.subscribe((params) => {
      const id = params['id'];
      if (id) {
        this.storeSubscription = this.store
          .select(selectAccessiblePropertyById(id))
          .subscribe((property) => (this.property = property));
      }
    });
  }

  ngOnDestroy() {
    this.routeSubscription?.unsubscribe();
    this.storeSubscription?.unsubscribe();
  }
}
