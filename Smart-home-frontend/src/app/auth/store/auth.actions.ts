import { createAction, props, union } from '@ngrx/store';
import { AuthToken } from '../model/auth-token.model';

export const login = createAction(
  '[Auth] Login Start',
  props<{ email: string; password: string }>()
);

export const login_success = createAction(
  '[Auth] Login Success',
  props<AuthToken>()
);

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
  login_success,
  sign_up,
  sign_up_success,
  confirm_email,
  confirm_email_success,
});

export type AuthActionsUnion = typeof all;
