import { Component, Input, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';

@Component({
  selector: 'app-events-pie-chart',
  templateUrl: './events-pie-chart.component.html',
  styleUrls: ['./events-pie-chart.component.scss'],
})
export class EventsPieChartComponent implements OnInit {
  @Input() logsCount = 0;
  @Input() messagesCount = 0;
  @Input() alarmsCount = 0;
  labels: string[] = ['Logs', 'Messages', 'Alarms'];
  data: ChartData<'doughnut'>;

  ngOnInit(): void {
    this.data = {
      labels: this.labels,
      datasets: [
        {
          data: [this.logsCount, this.messagesCount, this.alarmsCount],
          backgroundColor: ['#bdbdbd', '#424242', '#91e85b'],
        },
      ],
    };
  }
}
