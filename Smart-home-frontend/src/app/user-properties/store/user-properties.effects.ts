import { Injectable } from '@angular/core';
import { UserPropertiesHttpService } from '../services/user-properties-http.service';
import { NotifierService } from '../../core/notifier.service';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as UserPropertiesActions from './user-properties.actions';
import { map, switchMap } from 'rxjs';

@Injectable()
export class UserPropertiesEffects {
  getAccessibleProperties = createEffect(() =>
    this.actions$.pipe(
      ofType(UserPropertiesActions.getAccessibleProperties.type),
      switchMap(() =>
        this.httpService
          .getAccessibleProperties()
          .pipe(
            map((properties) =>
              UserPropertiesActions.setAccessibleProperties({ properties })
            )
          )
      )
    )
  );

  getPropertyMessages = createEffect(() =>
    this.actions$.pipe(
      ofType(UserPropertiesActions.getPropertyMessages.type),
      switchMap((action) =>
        this.httpService
          .getPropertyMessages(action.propertyId)
          .pipe(
            map((propertyMessages) =>
              UserPropertiesActions.setPropertyMessages({ propertyMessages })
            )
          )
      )
    )
  );

  searchPropertyMessages = createEffect(() =>
    this.actions$.pipe(
      ofType(UserPropertiesActions.searchPropertyMessages.type),
      switchMap((action) =>
        this.httpService
          .searchPropertyMessages(
            action.propertyId,
            action.filter,
            action.pageNumber,
            action.pageSize
          )
          .pipe(
            map((propertyMessages) =>
              UserPropertiesActions.setPropertyMessages({ propertyMessages })
            )
          )
      )
    )
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<UserPropertiesActions.UserPropertiesActionsUnion>,
    private httpService: UserPropertiesHttpService
  ) {}
}
