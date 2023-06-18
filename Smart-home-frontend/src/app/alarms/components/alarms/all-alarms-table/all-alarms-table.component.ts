import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Store } from '@ngrx/store';
import { getAlarms } from '../../../store/alarms.actions';
import { selectAlarmPage } from '../../../store/alarms.selectors';
import { Alarm } from '../../../../shared/model/alarm.model';
import { AlarmType } from '../../../../shared/model/alarm-type';

@Component({
  selector: 'app-all-alarms-table',
  templateUrl: './all-alarms-table.component.html',
  styleUrls: ['./all-alarms-table.component.scss'],
})
export class AllAlarmsTableComponent implements OnInit, OnDestroy {
  displayedColumns = ['alarmType', 'time', 'message', 'userEmail'];
  dataSource!: MatTableDataSource<Alarm>;
  private storeSubscription!: Subscription;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  totalElements = 0;
  pageSize = 10;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectAlarmPage)
      .subscribe((alarmPage) => {
        let alarms: Alarm[] = [];
        if (alarmPage) {
          alarms = alarmPage.items;
          this.totalElements = alarmPage.totalElements;
          this.pageSize = alarmPage.pageSize;
          if (this.paginator) {
            this.paginator.pageIndex = alarmPage.pageNumber;
          }
        }
        this.dataSource = new MatTableDataSource(alarms);
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  searchAlarms($event?: PageEvent) {
    const pageSize = $event ? $event.pageSize : this.pageSize;
    const pageNumber = $event ? $event.pageIndex : 0;
    this.store.dispatch(
      getAlarms({
        pageSize,
        pageNumber,
      })
    );
  }

  protected readonly AlarmType = AlarmType;
}
