import { createAction, props, union } from '@ngrx/store';
import { Property } from '../../shared/model/property';
import { PageResponse } from '../../shared/model/page-response';

export const getAccessibleProperties = createAction(
  '[User Properties] Get Accessible Properties'
);

export const setAccessibleProperties = createAction(
  '[User Properties] Set Accessible Properties',
  props<{ properties: Property[] }>()
);

export const getPropertyMessages = createAction(
  '[User Properties] Get Property Messages',
  props<{
    propertyId: string;
  }>()
);

export const searchPropertyMessages = createAction(
  '[User Properties] Search Property Messages',
  props<{
    propertyId: string;
    pageSize?: number;
    pageNumber?: number;
    filter?: string;
  }>()
);

export const setPropertyMessages = createAction(
  '[User Properties] Set Property Messages',
  props<{ propertyMessages: PageResponse<string> }>()
);

const all = union({
  getAccessibleProperties,
  setAccessibleProperties,
  getPropertyMessages,
  setPropertyMessages,
  searchPropertyMessages,
});

export type UserPropertiesActionsUnion = typeof all;
