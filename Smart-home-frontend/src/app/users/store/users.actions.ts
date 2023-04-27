import { createAction, props, union } from '@ngrx/store';
import { User } from '../model/user.model';

export const getUsers = createAction('[Users] Get Users');

export const setUsers = createAction(
  '[Users] Set Users',
  props<{ users: User[] }>()
);

export const deleteUser = createAction(
  '[Users] Delete User',
  props<{ id: string }>()
);

export const changeUserRole = createAction(
  '[Users] Change User Role',
  props<{ id: string; role: string }>()
);

export const userChangeSuccess = createAction(
  '[Users] User Change Success',
  props<{ id: string; user: User }>()
);

export const deleteUserSuccess = createAction(
  '[Users] Delete User Success',
  props<{ id: string }>()
);

const all = union({
  getUsers,
  setUsers,
  deleteUser,
  changeUserRole,
  userChangeSuccess,
  deleteUserSuccess,
});

export type UsersActionsUnion = typeof all;
