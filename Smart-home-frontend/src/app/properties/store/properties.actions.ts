import { createAction, props, union } from '@ngrx/store';
import { Property } from '../../shared/model/property';

export const getUserProperties = createAction(
  '[Properties] Get User Properties',
  props<{ id: string }>()
);

export const getSelfProperties = createAction(
  '[Properties] Get Self Properties'
);

export const setUserProperties = createAction(
  '[Properties] Set User Properties',
  props<{ properties: Property[] }>()
);

export const createProperty = createAction(
  '[Properties] Create Property',
  props<{ name: string; address: string; ownerId: string }>()
);

export const createPropertySuccess = createAction(
  '[Properties] Create Property Success'
);

export const deleteProperty = createAction(
  '[Properties] Delete Property',
  props<{ id: string }>()
);

export const deletePropertySuccess = createAction(
  '[Properties] Delete Property Success'
);

export const addPropertyMember = createAction(
  '[Properties] Add Property Member',
  props<{ propertyId: string; userId: string }>()
);

export const addPropertyMemberSuccess = createAction(
  '[Properties] Add Property Success'
);

export const removePropertyMember = createAction(
  '[Properties] Remove Property Member',
  props<{ propertyId: string; userId: string }>()
);

export const removePropertyMemberSuccess = createAction(
  '[Properties] Remove Property Member Success'
);

export const searchAddress = createAction(
  '[Properties] Search Address',
  props<{ value: string }>()
);

export const setSearchAddressResult = createAction(
  '[Properties] Set Search Address Result',
  props<{ searchAddressResults: string[] }>()
);

const all = union({
  getUserProperties,
  getSelfProperties,
  setUserProperties,
  createProperty,
  createPropertySuccess,
  deleteProperty,
  deletePropertySuccess,
  addPropertyMember,
  addPropertyMemberSuccess,
  removePropertyMember,
  removePropertyMemberSuccess,
  searchAddress,
  setSearchAddressResult,
});

export type PropertiesActionsUnion = typeof all;
