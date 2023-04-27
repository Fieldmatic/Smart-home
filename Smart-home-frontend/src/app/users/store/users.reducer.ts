import { Action, createReducer, on } from '@ngrx/store';
import * as UsersActions from './users.actions';
import { User } from '../model/user.model';

export interface State {
  users: User[];
}

const initialState: State = {
  users: [],
};

const usersReducer = createReducer(
  initialState,
  on(UsersActions.setUsers, (state, { users }) => ({
    ...state,
    users,
  })),
  on(UsersActions.userChangeSuccess, (state, { id, user }) => {
    const updatedUsers = state.users.map((u) => (u.id === id ? user : u));
    return { ...state, users: updatedUsers };
  }),
  on(UsersActions.deleteUserSuccess, (state, { id }) => {
    const updatedUsers = state.users.filter((u) => u.id !== id);
    return { ...state, users: updatedUsers };
  })
);

export function reducer(state: State | undefined, action: Action) {
  return usersReducer(state, action);
}
