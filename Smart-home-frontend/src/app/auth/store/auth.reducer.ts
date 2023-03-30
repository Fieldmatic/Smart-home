import { AuthToken } from '../model/auth-token.model';
import { Action, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';

export interface State {
  token: AuthToken | null;
}

const initialState: State = {
  token: null,
};

const authReducer = createReducer(
  initialState,
  on(AuthActions.login_success, (state, { token, role }) => ({
    ...state,
    token: new AuthToken(token, role),
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
