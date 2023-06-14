import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { Property } from '../../shared/model/property';
import { Store } from '@ngrx/store';
import * as UserPropertiesActions from '../store/user-properties.actions';
import { Actions, ofType } from '@ngrx/effects';

@Injectable({
  providedIn: 'root',
})
export class AccessiblePropertiesResolver implements Resolve<Property[]> {
  constructor(
    private store: Store,
    private actions$: Actions<UserPropertiesActions.UserPropertiesActionsUnion>
  ) {}

  resolve(): Observable<Property[]> {
    this.store.dispatch(UserPropertiesActions.getAccessibleProperties());
    return this.actions$.pipe(
      ofType(UserPropertiesActions.setAccessibleProperties.type),
      take(1),
      map((action) => action.properties)
    );
  }
}
