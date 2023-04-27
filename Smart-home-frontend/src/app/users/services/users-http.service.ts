import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersHttpService {
  GET_USERS = 'user/all';
  DELETE_USER = 'user/';
  CHANGE_USER_ROLE = 'user/';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getUsers() {
    return this.http.get<User[]>(this.config.apiEndpoint + this.GET_USERS);
  }

  deleteUser(id: string) {
    return this.http.delete(this.config.apiEndpoint + this.DELETE_USER + id);
  }

  changeUserRole(id: string, role: string) {
    return this.http.put<User>(
      this.config.apiEndpoint + this.CHANGE_USER_ROLE + id,
      {
        roleName: role,
      }
    );
  }
}
