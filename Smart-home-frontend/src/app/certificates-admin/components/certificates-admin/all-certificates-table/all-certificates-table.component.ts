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
import {
  delete_certificate,
  get_certificates,
} from '../../../store/certificates-admin.actions';
import { selectCertificates } from '../../../store/certificates-admin.selectors';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';

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
    this.store.dispatch(get_certificates());
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

  deleteCertificate(email: string) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Certificate Deletion',
        text: `Are you sure you want to delete the certificate of user ${email}?`,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'confirm') {
        this.store.dispatch(delete_certificate({ alias: email }));
      }
    });
  }
}
