import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './properties.reducer';

export const selectPropertiesState = createFeatureSelector<State>('properties');

export const selectUserProperties = createSelector(
  selectPropertiesState,
  (state) => state.userProperties
);

export const selectPropertyWithId = (id: string) =>
  createSelector(
    selectUserProperties,
    (properties) => properties.filter((property) => property.uuid === id)[0]
  );

export const selectAddressSearchResults = createSelector(
  selectPropertiesState,
  (state) => state.addressSearchResults
);

export const selectLogsPage = createSelector(
  selectPropertiesState, (state) => state.logPage
);
