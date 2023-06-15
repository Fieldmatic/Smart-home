import { createAction, props, union } from '@ngrx/store';
import { PageResponse } from '../../shared/model/page-response';
import {Alarm} from "../../shared/model/alarm.model";

export const getAlarms = createAction(
  '[Alarms] Get Alarms',
  props<{
    pageSize?: number;
    pageNumber?: number;
  }>()
);

export const setAlarms = createAction(
  '[Alarms] Set Alarms',
  props<{ alarmPage: PageResponse<Alarm> }>()
);


const all = union({
  getAlarms,
  setAlarms,
});

export type AlarmActionsUnion = typeof all;
