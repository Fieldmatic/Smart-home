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
import {
  get_pending_csrs,
  reject_csr,
} from '../../../store/certificates-admin.actions';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

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

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.store.dispatch(get_pending_csrs());
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
    this.store.dispatch(reject_csr({ id }));
  }

  acceptCsr(id: string) {
    console.log('ACCEPT ' + id);
  }
}
