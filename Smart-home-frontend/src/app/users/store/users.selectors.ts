import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './users.reducer';

export const selectUsersState = createFeatureSelector<State>('users');

export const selectUsers = createSelector(
  selectUsersState,
  (state) => state.users
);
