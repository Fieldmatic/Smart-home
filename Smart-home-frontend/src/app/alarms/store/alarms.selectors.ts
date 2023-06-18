import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './alarms.reducer';

export const selectAlarmsState = createFeatureSelector<State>('alarms');

export const selectAlarmPage = createSelector(
  selectAlarmsState,
  (state) => state.alarmPage
);

export const selectAlarms = createSelector(selectAlarmPage, (alarmPage) =>
  alarmPage ? alarmPage.items : []
);

