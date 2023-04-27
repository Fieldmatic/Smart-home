import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatSort } from '@angular/material/sort';
import { Store } from '@ngrx/store';
import { User } from '../../../model/user.model';
import { selectUsers } from '../../../store/users.selectors';

@Component({
  selector: 'app-all-users-table',
  templateUrl: './all-users-table.component.html',
  styleUrls: ['./all-users-table.component.scss'],
})
export class AllUsersTableComponent
  implements OnInit, OnDestroy, AfterViewInit
{
  displayedColumns = ['email', 'role'];
  dataSource!: MatTableDataSource<User>;
  storeSubscription!: Subscription;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectUsers)
      .subscribe((users) => {
        this.dataSource = new MatTableDataSource(users);
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }
}
