import { Component } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],
})
export class ReportComponent {
  reportGroup: FormGroup<{ start: FormControl; end: FormControl }> =
    new FormGroup({
      start: new FormControl(new Date(), Validators.required),
      end: new FormControl(new Date(), Validators.required),
    });

  constructor(private reportService: ReportService) {}

  generateReport() {
    if (this.reportGroup.valid) {
      const start = this.reportGroup.controls.start.value;
      const end = this.reportGroup.controls.end.value;
      this.reportService.getReport(start, end);
    }
  }
}
