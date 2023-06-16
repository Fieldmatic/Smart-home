import { Component, OnDestroy, OnInit } from '@angular/core';
import { Property } from '../../../../shared/model/property';
import { Observable, Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import {
  selectLogsPage,
  selectPropertyWithId,
} from '../../../store/properties.selectors';
import { selectRole } from '../../../../auth/store/auth.selectors';
import { LogResponse } from '../../../model/log-response';
import { PageResponse } from '../../../../shared/model/page-response';

@Component({
  selector: 'app-property-profile',
  templateUrl: './property-profile.component.html',
  styleUrls: ['./property-profile.component.scss'],
})
export class PropertyProfileComponent implements OnInit, OnDestroy {
  property!: Property;
  private propertyStoreSubscription!: Subscription;
  propertyLogsPage!: Observable<PageResponse<LogResponse>>;
  private authStoreSubscription!: Subscription;
  loggedInUsersRole: string | null | undefined;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.propertyStoreSubscription = this.store
      .select(selectPropertyWithId(id))
      .subscribe((property) => {
        this.property = property;
      });
    this.authStoreSubscription = this.store
      .select(selectRole)
      .subscribe((role) => {
        this.loggedInUsersRole = role;
      });
    this.propertyLogsPage = this.store.select(selectLogsPage);
  }

  ngOnDestroy() {
    this.propertyStoreSubscription.unsubscribe();
    this.authStoreSubscription.unsubscribe();
  }
}
