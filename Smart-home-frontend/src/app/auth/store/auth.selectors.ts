import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './auth.reducer';

export const selectAuth = createFeatureSelector<State>('auth');

export const selectToken = createSelector(
  selectAuth,
  (state: State) => state.token
);
