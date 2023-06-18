import { Component, OnDestroy, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss'],
})
export class ReportComponent implements OnInit, OnDestroy {
  reportGroup: FormGroup<{ start: FormControl; end: FormControl }> =
    new FormGroup({
      start: new FormControl(new Date(), Validators.required),
      end: new FormControl(new Date(), Validators.required),
    });
  private routeSubscription!: Subscription;
  private propertyId!: string;

  constructor(
    private reportService: ReportService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.routeSubscription = this.route.params.subscribe((params) => {
      const id = params['id'];
      if (id) {
        this.propertyId = id;
      }
    });
  }

  ngOnDestroy() {
    this.routeSubscription.unsubscribe();
  }

  generateReport() {
    if (this.reportGroup.valid && this.propertyId) {
      const start = this.reportGroup.controls.start.value;
      const end = this.reportGroup.controls.end.value;
      this.reportService.getReport(this.propertyId, start, end);
    }
  }
}
