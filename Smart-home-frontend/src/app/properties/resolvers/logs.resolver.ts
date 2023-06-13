import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router } from "@angular/router";
import { map, Observable, take } from 'rxjs';
import { User } from '../../shared/model/user.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as PropertyActions from '../store/properties.actions';
import { LogResponse } from "../model/log-response";

@Injectable({
  providedIn: 'root',
})
export class LogsResolver implements Resolve<LogResponse[]> {
  constructor(
    private store: Store,
    private actions$: Actions<PropertyActions.PropertiesActionsUnion>,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<LogResponse[]> | Promise<LogResponse[]> | LogResponse[] {
    const id = route.params['id'];
    console.log(id);
    this.store.dispatch(PropertyActions.getLogsForProperty({id: id}));
    return this.actions$.pipe(
      ofType(PropertyActions.setLogs.type),
      take(1),
      map((action) => action.logPage.items)
    );
  }
}
