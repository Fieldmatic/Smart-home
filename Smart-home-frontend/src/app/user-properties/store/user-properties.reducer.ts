import { Property } from '../../shared/model/property';
import { Action, createReducer, on } from '@ngrx/store';
import * as UserPropertiesActions from './user-properties.actions';
import { PageResponse } from '../../shared/model/page-response';

export interface State {
  accessibleProperties: Property[];
  propertyMessages: PageResponse<string> | void;
}

const initialState: State = {
  accessibleProperties: [],
  propertyMessages: undefined,
};

const userPropertiesReducer = createReducer(
  initialState,
  on(
    UserPropertiesActions.setAccessibleProperties,
    (state, { properties }) => ({
      ...state,
      accessibleProperties: properties,
    })
  ),
  on(
    UserPropertiesActions.setPropertyMessages,
    (state, { propertyMessages }) => ({
      ...state,
      propertyMessages,
    })
  )
);

export function reducer(state: State | undefined, action: Action) {
  return userPropertiesReducer(state, action);
}
