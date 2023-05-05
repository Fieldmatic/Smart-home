import { Action, createReducer, on } from '@ngrx/store';
import * as UsersActions from './users.actions';
import { User } from '../../shared/model/user.model';
import { PageResponse } from '../../shared/model/page-response';

export interface State {
  userPage: PageResponse<User> | void;
  userEmailsSearchResult: User[];
}

const initialState: State = {
  userPage: undefined,
  userEmailsSearchResult: [],
};

const usersReducer = createReducer(
  initialState,
  on(UsersActions.setUsers, (state, { userPage }) => ({
    ...state,
    userPage: userPage,
  })),
  on(UsersActions.setUserEmailsSearchResult, (state, { users }) => ({
    ...state,
    userEmailsSearchResult: users,
  }))
);

export function reducer(state: State | undefined, action: Action) {
  return usersReducer(state, action);
}
