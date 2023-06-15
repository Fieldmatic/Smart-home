import { Action, createReducer, on } from '@ngrx/store';
import * as  AlarmsActions from './alarms.actions';
import { PageResponse } from '../../shared/model/page-response';
import {Alarm} from "../../shared/model/alarm.model";

export interface State {
  alarmPage: PageResponse<Alarm> | void;
}

const initialState: State = {
  alarmPage: undefined,
};

const alarmsReducer = createReducer(
  initialState,
  on(AlarmsActions.setAlarms, (state, { alarmPage }) => ({
    ...state,
    alarmPage: alarmPage,
  })),
);

export function reducer(state: State | undefined, action: Action) {
  return alarmsReducer(state, action);
}
