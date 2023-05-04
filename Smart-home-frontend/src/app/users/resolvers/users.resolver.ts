import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  Resolve,
  RouterStateSnapshot,
} from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { User } from '../model/user.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as UsersActions from '../store/users.actions';

@Injectable({
  providedIn: 'root',
})
export class UsersResolver implements Resolve<User[]> {
  constructor(
    private store: Store,
    private actions$: Actions<UsersActions.UsersActionsUnion>
  ) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<User[]> | Promise<User[]> | User[] {
    this.store.dispatch(UsersActions.getUsers({}));
    return this.actions$.pipe(
      ofType(UsersActions.setUsers.type),
      take(1),
      map((action) => action.userPage.items)
    );
  }
}
