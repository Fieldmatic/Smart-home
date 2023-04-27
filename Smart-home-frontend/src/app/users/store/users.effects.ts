import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { NotifierService } from '../../core/notifier.service';
import { Router } from '@angular/router';
import { UsersHttpService } from '../services/users-http.service';
import * as UsersActions from './users.actions';
import { map, switchMap } from 'rxjs';

@Injectable()
export class UsersEffects {
  getUsers = createEffect(() => {
    return this.actions$.pipe(
      ofType(UsersActions.getUsers.type),
      switchMap(() => {
        return this.httpService
          .getUsers()
          .pipe(map((users) => UsersActions.setUsers({ users })));
      })
    );
  });

  deleteUser = createEffect(() => {
    return this.actions$.pipe(
      ofType(UsersActions.deleteUser.type),
      switchMap((action) => {
        return this.httpService
          .deleteUser(action.id)
          .pipe(map(() => UsersActions.deleteUserSuccess({ id: action.id })));
      })
    );
  });

  changeUserRole = createEffect(() => {
    return this.actions$.pipe(
      ofType(UsersActions.changeUserRole.type),
      switchMap((action) => {
        return this.httpService
          .changeUserRole(action.id, action.role)
          .pipe(
            map((user) =>
              UsersActions.userChangeSuccess({ id: action.id, user })
            )
          );
      })
    );
  });

  userChangedSuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(UsersActions.userChangeSuccess.type),
        map(() => {
          const message = 'You have successfully updated user.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  deleteUserSuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(UsersActions.deleteUserSuccess.type),
        map(() => {
          const message = 'You have successfully removed user.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<UsersActions.UsersActionsUnion>,
    private httpService: UsersHttpService
  ) {}
}
