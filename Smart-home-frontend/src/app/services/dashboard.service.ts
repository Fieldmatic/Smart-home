import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../app-config/app-config';
import { HttpClient } from '@angular/common/http';
import { AdminDashboard } from '../model/admin-dashboard';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private GET_ADMIN_DASHBOARD = 'dashboard/admin';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  public getAdminDashboard() {
    return this.http.get<AdminDashboard>(
      this.config.apiEndpoint + this.GET_ADMIN_DASHBOARD
    );
  }
}
