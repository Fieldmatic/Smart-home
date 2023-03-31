import { createAction, props, union } from '@ngrx/store';

export const login = createAction(
  '[Auth] Login Start',
  props<{ email: string; password: string }>()
);

export const login_success = createAction(
  '[Auth] Login Success',
  props<{ token: string }>()
);

export const auto_login = createAction('[Auth] Auto Login');

export const auto_login_fail = createAction('[Auth] Auto Login  Failed');

export const logout = createAction('[Auth] Logout');

export const sign_up = createAction(
  '[Auth] Sign Up Start',
  props<{ email: string; password: string; role: string }>()
);

export const sign_up_success = createAction('[Auth] Sign Up Success');

export const confirm_email = createAction(
  '[Auth] Confirm Email',
  props<{ token: string }>()
);

export const confirm_email_success = createAction(
  '[Auth] Confirm Email Success'
);

const all = union({
  login,
  auto_login,
  auto_login_fail,
  login_success,
  logout,
  sign_up,
  sign_up_success,
  confirm_email,
  confirm_email_success,
});

export type AuthActionsUnion = typeof all;
