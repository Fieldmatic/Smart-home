import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve } from "@angular/router";
import { map, Observable, take } from 'rxjs';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as PropertyActions from '../store/properties.actions';
import { LogResponse } from "../model/log-response";
import { PageResponse } from "../../shared/model/page-response";

@Injectable({
  providedIn: 'root',
})
export class LogsResolver implements Resolve<PageResponse<LogResponse>> {
  constructor(
    private store: Store,
    private actions$: Actions<PropertyActions.PropertiesActionsUnion>,
  ) {}

  resolve(route: ActivatedRouteSnapshot): Observable<PageResponse<LogResponse>>{
    const id = route.params['id'];
    this.store.dispatch(PropertyActions.getLogsForProperty({id: id}));
    return this.actions$.pipe(
      ofType(PropertyActions.setLogs.type),
      take(1),
      map((action) => action.logPage)
    );
  }
}
