import { Injectable } from '@angular/core';
import { ReportHttpService } from './report-http.service';
import { saveAs } from 'file-saver';

@Injectable({
  providedIn: 'root',
})
export class ReportService {
  constructor(private httpService: ReportHttpService) {}

  getReport(start: Date, end: Date) {
    this.httpService.getReport(start, end).subscribe((report) => {
      saveAs(report, 'Report.pdf');
    });
  }
}
