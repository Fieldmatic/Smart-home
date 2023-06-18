import { Action, createReducer, on } from '@ngrx/store';
import * as AuthActions from './auth.actions';

export interface State {
  userTryingToLogin: { email: string; password: string } | null;
  pinValidityTime: number | null;
  token: string | null;
}

const initialState: State = {
  userTryingToLogin: null,
  pinValidityTime: null,
  token: null,
};

const authReducer = createReducer(
  initialState,
  on(AuthActions.loginFirstStep, (state, { email, password }) => ({
    ...state,
    pinValidityTime: null,
    userTryingToLogin: { email, password },
  })),
  on(
    AuthActions.loginFirstStepSuccess,
    (state, { pinValidityTime, email, password }) => ({
      ...state,
      pinValidityTime,
      userTryingToLogin: { email, password },
    })
  ),
  on(AuthActions.loginSuccess, (state, { token }) => ({
    ...state,
    token,
    userTryingToLogin: null,
    pinValidityTime: null,
  })),
  on(AuthActions.logoutSuccess, (state) => ({
    ...state,
    token: null,
  })),
  on(AuthActions.autoLoginFail, (state) => ({
    ...state,
    token: null,
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
