import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../services/dashboard.service';
import { AdminDashboard } from '../../../model/admin-dashboard';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.scss'],
})
export class AdminHomePageComponent implements OnInit {
  adminDashboardData: AdminDashboard;

  constructor(private dashboardService: DashboardService) {}

  ngOnInit() {
    this.dashboardService.getAdminDashboard().subscribe((adminDashboard) => {
      this.adminDashboardData = adminDashboard;
    });
  }
}
