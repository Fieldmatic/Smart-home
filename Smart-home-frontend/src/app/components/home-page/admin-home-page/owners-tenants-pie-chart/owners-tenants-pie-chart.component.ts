import { Component, Input, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';

@Component({
  selector: 'app-owners-tenants-pie-chart',
  templateUrl: './owners-tenants-pie-chart.component.html',
  styleUrls: ['./owners-tenants-pie-chart.component.scss'],
})
export class OwnersTenantsPieChartComponent implements OnInit {
  @Input() ownersCount = 0;
  @Input() tenantsCount = 0;
  labels: string[] = ['Owners', 'Tenants'];
  data: ChartData<'doughnut'>;

  ngOnInit(): void {
    this.data = {
      labels: this.labels,
      datasets: [
        {
          data: [this.ownersCount, this.tenantsCount],
          backgroundColor: ['#91e85b', '#424242'],
        },
      ],
    };
  }
}
