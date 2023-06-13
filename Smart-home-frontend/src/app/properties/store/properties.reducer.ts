import { Action, createReducer, on } from '@ngrx/store';
import { Property } from '../../shared/model/property';
import * as PropertiesActions from '../store/properties.actions';
import { PageResponse } from "../../shared/model/page-response";
import { LogResponse } from "../model/log-response";

export interface State {
  userProperties: Property[];
  addressSearchResults: string[];
  logPage: PageResponse<LogResponse> | void;
}

const initialState: State = {
  userProperties: [],
  addressSearchResults: [],
  logPage: undefined,
};

const propertiesReducer = createReducer(
  initialState,
  on(PropertiesActions.setUserProperties, (state, { properties }) => ({
    ...state,
    userProperties: properties,
  })),
  on(
    PropertiesActions.setSearchAddressResult,
    (state, { searchAddressResults }) => ({
      ...state,
      addressSearchResults: searchAddressResults,
    })
  ),
  on(PropertiesActions.createPropertySuccess, (state, { property }) => {
    return { ...state, userProperties: [...state.userProperties, property] };
  }),
  on(PropertiesActions.deletePropertySuccess, (state, { propertyId }) => {
    const updatedUserProperties = state.userProperties.filter(
      (property) => property.uuid !== propertyId
    );
    return { ...state, userProperties: updatedUserProperties };
  }),
  on(
    PropertiesActions.updatePropertySuccess,
    PropertiesActions.removePropertyMemberSuccess,
    (state, { property }) => {
      const updatedUserProperties = state.userProperties.map((p) =>
        property.uuid === p.uuid ? property : p
      );
      return { ...state, userProperties: updatedUserProperties };
    }
  ),
  on(PropertiesActions.setLogs, (state, { logPage }) => ({
    ...state,
    logPage: logPage,
  })),
);

export function reducer(state: State | undefined, action: Action) {
  return propertiesReducer(state, action);
}
