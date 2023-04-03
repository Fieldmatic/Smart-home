import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { CSR } from '../../../model/CSR.model';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { selectCSRs } from '../../../store/certificates-admin.selectors';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogWithReasonOptionsComponent } from '../../../../shared/components/confirmation-dialog-with-reason-options/confirmation-dialog-with-reason-options.component';
import { rejectCSR } from '../../../store/certificates-admin.actions';

@Component({
  selector: 'app-csr-pending-table',
  templateUrl: './csr-pending-table.component.html',
  styleUrls: ['./csr-pending-table.component.scss'],
})
export class CsrPendingTableComponent
  implements OnInit, OnDestroy, AfterViewInit
{
  displayedColumns = [
    'commonName',
    'email',
    'view',
    'keySize',
    'algorithm',
    'reject',
    'accept',
  ];
  dataSource!: MatTableDataSource<CSR>;
  storeSubscription!: Subscription;

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private store: Store, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.storeSubscription = this.store.select(selectCSRs).subscribe((csrs) => {
      this.dataSource = new MatTableDataSource<CSR>(csrs);
    });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  rejectCsr(id: string) {
    const dialogRef = this.dialog.open(
      ConfirmationDialogWithReasonOptionsComponent,
      {
        data: {
          title: 'CSR Rejection',
          text: `Are you sure you want to reject the CSR?`,
          options: ['Non-compliance with CA policies', 'Suspicious data'],
        },
      }
    );

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.store.dispatch(rejectCSR({ id, reason: result }));
      }
    });
  }
}
