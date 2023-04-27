import { createAction, props, union } from '@ngrx/store';

export const loginFirstStep = createAction(
  '[Auth] Login First Step',
  props<{ email: string; password: string }>()
);

export const loginFirstStepSuccess = createAction(
  '[Auth] Login First Step Success',
  props<{ email: string; password: string; pinValidityTime: number }>()
);

export const loginSecondStep = createAction(
  '[Auth] Login Second Step',
  props<{ email: string; password: string; pin: string }>()
);

export const loginSuccess = createAction(
  '[Auth] Login Success',
  props<{ token: string; cookie?: string }>()
);

export const loginFail = createAction('[Auth] Login Fail');

export const autoLogin = createAction('[Auth] Auto Login');

export const autoLoginFail = createAction('[Auth] Auto Login  Failed');

export const logout = createAction('[Auth] Logout');

export const logoutSuccess = createAction('[Auth] Logout Success');

export const signUp = createAction(
  '[Auth] Sign Up Start',
  props<{ email: string; password: string; role: string }>()
);

export const signUpSuccess = createAction('[Auth] Sign Up Success');

export const confirmEmail = createAction(
  '[Auth] Confirm Email',
  props<{ token: string }>()
);

export const confirmEmailSuccess = createAction('[Auth] Confirm Email Success');

const all = union({
  loginFirstStep,
  loginFirstStepSuccess,
  loginSecondStep,
  autoLogin,
  autoLoginFail,
  loginSuccess,
  loginFail,
  logout,
  logoutSuccess,
  signUp,
  signUpSuccess,
  confirmEmail,
  confirmEmailSuccess,
});

export type AuthActionsUnion = typeof all;
