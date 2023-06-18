import { Component, Input, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';

@Component({
  selector: 'app-events-linear-chart',
  templateUrl: './events-linear-chart.component.html',
  styleUrls: ['./events-linear-chart.component.scss'],
})
export class EventsLinearChartComponent implements OnInit {
  @Input() logsCountInLastYearByMonths: { [key: string]: number };
  @Input() messagesCountInLastYearByMonths: { [key: string]: number };
  @Input() alarmsCountInLastYearByMonths: { [key: string]: number };
  public data: ChartData<'line'>;

  ngOnInit() {
    this.data = {
      datasets: [
        {
          data: this.getMonthlyData(this.logsCountInLastYearByMonths),
          label: 'Logs',
          backgroundColor: 'rgba(189,189,189,0.4)',
          borderColor: '#bdbdbd',
          pointBackgroundColor: '#bdbdbd',
          fill: 'origin',
        },
        {
          data: this.getMonthlyData(this.messagesCountInLastYearByMonths),
          label: 'Messages',
          backgroundColor: 'rgba(66,66,66,0.2)',
          borderColor: '#424242',
          pointBackgroundColor: '#424242',
          fill: 'origin',
        },
        {
          data: this.getMonthlyData(this.alarmsCountInLastYearByMonths),
          label: 'Alarms',
          yAxisID: 'ID',
          backgroundColor: 'rgba(145,232,91,0.3)',
          borderColor: '#91e85b',
          pointBackgroundColor: '#91e85b',
          fill: 'origin',
        },
      ],
      labels: this.getPrevious12Months(),
    };
  }

  private getPrevious12Months(): string[] {
    const months: string[] = [];
    const currentDate = new Date();

    for (let i = 0; i < 12; i++) {
      const month = currentDate.getMonth() - i;
      const previousMonth = new Date(currentDate.getFullYear(), month, 1);
      const monthName = previousMonth.toLocaleString('default', {
        month: 'long',
      });
      months.unshift(monthName);
    }

    return months;
  }

  private getMonthlyData(dataByMonths: { [key: string]: number }): number[] {
    const months: string[] = this.getPrevious12Months();
    const monthlyData: number[] = [];

    for (let i = 0; i < 12; i++) {
      const month = months[i];
      const count = dataByMonths[month] || 0;
      monthlyData.push(count);
    }

    return monthlyData;
  }
}
