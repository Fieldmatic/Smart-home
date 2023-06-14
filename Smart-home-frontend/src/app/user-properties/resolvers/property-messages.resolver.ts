import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as UserPropertiesActions from '../store/user-properties.actions';
import { PageResponse } from '../../shared/model/page-response';

@Injectable({
  providedIn: 'root',
})
export class PropertyMessagesResolver implements Resolve<PageResponse<string>> {
  constructor(
    private store: Store,
    private actions$: Actions<UserPropertiesActions.UserPropertiesActionsUnion>
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<PageResponse<string>> {
    const propertyId = route.params['id'];
    this.store.dispatch(
      UserPropertiesActions.getPropertyMessages({ propertyId })
    );
    return this.actions$.pipe(
      ofType(UserPropertiesActions.setPropertyMessages.type),
      take(1),
      map((action) => action.propertyMessages)
    );
  }
}
