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

  getReport(start: Date, end: Date) {
    const formattedStart = start.toISOString().split('T')[0];
    const formattedEnd = end.toISOString().split('T')[0];
    const params = new HttpParams()
      .append('start', formattedStart)
      .append('end', formattedEnd);
    return this.http.get(this.config.apiEndpoint + this.GET_REPORT, {
      params,
      responseType: 'blob',
    });
  }
}
