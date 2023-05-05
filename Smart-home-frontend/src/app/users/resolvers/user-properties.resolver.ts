import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { Property } from '../../shared/model/property';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as PropertiesActions from '../../properties/store/properties.actions';

@Injectable({
  providedIn: 'root',
})
export class UserPropertiesResolver implements Resolve<Property[]> {
  constructor(
    private store: Store,
    private actions$: Actions<PropertiesActions.PropertiesActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot
  ): Observable<Property[]> | Promise<Property[]> | Property[] {
    this.store.dispatch(
      PropertiesActions.getUserProperties({ id: route.params['id'] })
    );
    return this.actions$.pipe(
      ofType(PropertiesActions.setUserProperties.type),
      take(1),
      map((action) => action.properties)
    );
  }
}
