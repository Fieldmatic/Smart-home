import { Action, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';

export interface State {
  token: string | null;
}

const initialState: State = {
  token: null,
};

const authReducer = createReducer(
  initialState,
  on(AuthActions.login_success, (state, { token }) => ({
    ...state,
    token: token,
  })),
  on(AuthActions.logout, (state) => ({
    ...state,
    token: null,
  })),
  on(AuthActions.auto_login_fail, (state) => ({
    ...state,
    token: null,
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
