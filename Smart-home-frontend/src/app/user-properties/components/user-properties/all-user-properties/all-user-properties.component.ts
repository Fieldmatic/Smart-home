import { Component, OnDestroy, OnInit } from '@angular/core';
import { Property } from '../../../../shared/model/property';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectAccessibleProperties } from '../../../store/user-properties.selectors';

@Component({
  selector: 'app-all-user-properties',
  templateUrl: './all-user-properties.component.html',
  styleUrls: ['./all-user-properties.component.scss'],
})
export class AllUserPropertiesComponent implements OnInit, OnDestroy {
  properties: Property[] = [];
  private storeSubscription!: Subscription;

  constructor(private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectAccessibleProperties)
      .subscribe((properties) => (this.properties = properties));
  }

  ngOnDestroy() {
    this.storeSubscription.unsubscribe();
  }
}
