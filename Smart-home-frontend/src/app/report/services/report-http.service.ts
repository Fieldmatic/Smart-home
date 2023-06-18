import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ReportHttpService {
  private GET_REPORT = 'report/';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getReport(id: string, start: Date, end: Date) {
    const formattedStart = this.formatDate(start);
    const formattedEnd = this.formatDate(end);
    const params = new HttpParams()
      .append('start', formattedStart)
      .append('end', formattedEnd);
    return this.http.get(this.config.apiEndpoint + this.GET_REPORT + id, {
      params,
      responseType: 'blob',
    });
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
}
