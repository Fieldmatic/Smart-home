import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { map, Observable, take } from 'rxjs';
import { User } from '../../shared/model/user.model';
import { Store } from '@ngrx/store';
import { Actions, ofType } from '@ngrx/effects';
import * as AlarmsActions from '../store/alarms.actions';
import {Alarm} from "../../shared/model/alarm.model";

@Injectable({
  providedIn: 'root',
})
export class AlarmsResolver implements Resolve<Alarm[]> {
  constructor(
    private store: Store,
    private actions$: Actions<AlarmsActions.AlarmActionsUnion>
  ) {}

  resolve(): Observable<Alarm[]> | Promise<Alarm[]> | Alarm[] {
    this.store.dispatch(AlarmsActions.getAlarms({}));
    return this.actions$.pipe(
      ofType(AlarmsActions.setAlarms.type),
      take(1),
      map((action) => action.alarmPage.items)
    );
  }
}
