import { createAction, props, union } from '@ngrx/store';
import { Property } from '../../shared/model/property';

export const getAccessibleProperties = createAction(
  '[User Properties] Get Accessible Properties'
);

export const setAccessibleProperties = createAction(
  '[User Properties] Set Accessible Properties',
  props<{ properties: Property[] }>()
);

export const getPropertyMessages = createAction(
  '[User Properties] Get Property Messages',
  props<{ propertyId: string }>()
);

export const setPropertyMessages = createAction(
  '[User Properties] Set Property Messages',
  props<{ propertyMessages: string[] }>()
);

const all = union({
  getAccessibleProperties,
  setAccessibleProperties,
  getPropertyMessages,
  setPropertyMessages,
});

export type UserPropertiesActionsUnion = typeof all;
