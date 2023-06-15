import { Actions, createEffect, ofType } from '@ngrx/effects';
import { NotifierService } from '../../core/notifier.service';
import { Router } from '@angular/router';
import {AlarmsHttpService} from "../services/alarms-http.service";
import * as AlarmsActions from './alarms.actions';
import { map, switchMap } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class AlarmsEffects {
  getUsers = createEffect(() => {
    return this.actions$.pipe(
      ofType(AlarmsActions.getAlarms.type),
      switchMap((action) => {
        return this.httpService
          .getAlarms(
            action.pageSize,
            action.pageNumber
          )
          .pipe(map((alarmPage) => AlarmsActions.setAlarms({ alarmPage })));
      })
    );
  });

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<AlarmsActions.AlarmActionsUnion>,
    private httpService: AlarmsHttpService
  ) {}
}
