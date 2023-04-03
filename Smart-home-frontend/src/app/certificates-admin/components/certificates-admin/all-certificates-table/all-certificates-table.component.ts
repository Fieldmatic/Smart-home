import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Certificate } from '../../../model/certificate.model';
import { MatSort } from '@angular/material/sort';
import { Store } from '@ngrx/store';
import { selectCertificates } from '../../../store/certificates-admin.selectors';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { revokeCertificate } from '../../../store/certificates-admin.actions';
import { ConfirmationDialogWithReasonOptionsComponent } from '../../../../shared/components/confirmation-dialog-with-reason-options/confirmation-dialog-with-reason-options.component';

@Component({
  selector: 'app-all-certificates-table',
  templateUrl: './all-certificates-table.component.html',
  styleUrls: ['./all-certificates-table.component.scss'],
})
export class AllCertificatesTableComponent
  implements OnInit, OnDestroy, AfterViewInit
{
  displayedColumns = [
    'email',
    'issuedFor',
    'start',
    'end',
    'isValid',
    'disable',
  ];
  dataSource!: MatTableDataSource<Certificate>;
  storeSubscription!: Subscription;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private store: Store, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectCertificates)
      .subscribe((certificates) => {
        this.dataSource = new MatTableDataSource<Certificate>(certificates);
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  revokeCertificate(email: string) {
    const options = [
      'Non-specific reason',
      'Private key has been compromised',
      "Issuing CA's private key has been compromised",
    ];
    const dialogRef = this.dialog.open(
      ConfirmationDialogWithReasonOptionsComponent,
      {
        data: {
          title: 'Certificate Revocation',
          text: `Are you sure you want to revoke the certificate?`,
          addCustom: false,
          options,
        },
      }
    );

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const reason = options.findIndex(result);
        this.store.dispatch(
          revokeCertificate({ alias: email, reason, message: result })
        );
      }
    });
  }
}
