import { createAction, props, union } from '@ngrx/store';
import { Property } from '../../shared/model/property';
import {DeviceType} from "../../shared/model/device-type";
import { SortDirection } from "../../shared/model/sort-direction";
import { PageResponse } from "../../shared/model/page-response";
import { User } from "../../shared/model/user.model";
import { LogResponse } from "../model/log-response";

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
  '[Properties] Create Property Success',
  props<{ property: Property }>()
);

export const deleteProperty = createAction(
  '[Properties] Delete Property',
  props<{ id: string; ownerId: string }>()
);

export const deletePropertySuccess = createAction(
  '[Properties] Delete Property Success',
  props<{ propertyId: string; ownerId: string }>()
);

export const addPropertyMember = createAction(
  '[Properties] Add Property Member',
  props<{ propertyId: string; userId: string }>()
);

export const updatePropertySuccess = createAction(
  '[Properties] Update Property Success',
  props<{ property: Property, message: string }>()
);

export const removePropertyMember = createAction(
  '[Properties] Remove Property Member',
  props<{ propertyId: string; userId: string }>()
);

export const removePropertyMemberSuccess = createAction(
  '[Properties] Remove Property Member Success',
  props<{ property: Property }>()
);

export const searchAddress = createAction(
  '[Properties] Search Address',
  props<{ value: string }>()
);

export const setSearchAddressResult = createAction(
  '[Properties] Set Search Address Result',
  props<{ searchAddressResults: string[] }>()
);

export const addPropertyDevice = createAction(
  '[Properties] Add Property Device',
  props<{ propertyId: string; name: string, deviceType: DeviceType, readPeriod: number, messageRegex: string }>()
)

export const getLogsForProperty = createAction(
  '[Properties] Get Logs',
  props<{ id: string,
          pageSize?: number;
          pageNumber?: number;
          search?: string; }>()
);

export const setLogs = createAction(
  '[Properties] Set Logs',
  props<{ logPage: PageResponse<LogResponse> }>()
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
  addPropertyMemberSuccess: updatePropertySuccess,
  removePropertyMember,
  removePropertyMemberSuccess,
  searchAddress,
  setSearchAddressResult,
  addPropertyDevice,
  getLogsForProperty,
  setLogs,
});

export type PropertiesActionsUnion = typeof all;
