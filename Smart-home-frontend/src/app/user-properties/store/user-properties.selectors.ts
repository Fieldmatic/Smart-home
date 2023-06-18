import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './user-properties.reducer';

export const selectUserProperties =
  createFeatureSelector<State>('user_properties');

export const selectAccessibleProperties = createSelector(
  selectUserProperties,
  (state) => state.accessibleProperties
);

export const selectAccessiblePropertyById = (id: string) =>
  createSelector(
    selectAccessibleProperties,
    (accessibleProperties) =>
      accessibleProperties.filter((property) => property.uuid === id)[0]
  );

export const selectPropertyMessages = createSelector(
  selectUserProperties,
  (state) => state.propertyMessages
);
