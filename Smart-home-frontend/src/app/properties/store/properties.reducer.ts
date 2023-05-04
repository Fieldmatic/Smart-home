import { Action, createReducer, on } from '@ngrx/store';
import { Property } from '../../shared/model/property';
import * as PropertiesActions from '../store/properties.actions';

export interface State {
  userProperties: Property[];
  addressSearchResults: string[];
}

const initialState: State = {
  userProperties: [],
  addressSearchResults: [],
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
  )
);

export function reducer(state: State | undefined, action: Action) {
  return propertiesReducer(state, action);
}
