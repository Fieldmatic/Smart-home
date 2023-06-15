import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Store } from '@ngrx/store';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { selectPropertyMessages } from '../../../../store/user-properties.selectors';
import { searchPropertyMessages } from '../../../../store/user-properties.actions';
import { textValidator } from '../../../../../shared/validators/text.validator';

@Component({
  selector: 'app-user-property-profile-messages',
  templateUrl: './user-property-profile-messages.component.html',
  styleUrls: ['./user-property-profile-messages.component.scss'],
})
export class UserPropertyProfileMessagesComponent implements OnInit, OnDestroy {
  displayedColumns = ['message'];
  dataSource!: MatTableDataSource<string>;
  private storeSubscription!: Subscription;
  filterAndSortForm: FormGroup<{
    searchContent: FormControl;
    deviceTypeFilter: FormControl;
  }> = new FormGroup({
    searchContent: new FormControl(null, textValidator),
    deviceTypeFilter: new FormControl(null),
  });
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  totalElements = 0;
  pageSize = 10;
  @Input() propertyId!: string;

  constructor(private store: Store) {}

  ngOnInit() {
    this.storeSubscription = this.store
      .select(selectPropertyMessages)
      .subscribe((messagePage) => {
        let messages: string[] = [];
        if (messagePage) {
          messages = messagePage.items;
          this.totalElements = messagePage.totalElements;
          this.pageSize = messagePage.pageSize;
          if (this.paginator) {
            this.paginator.pageIndex = messagePage.pageNumber;
          }
        }
        this.dataSource = new MatTableDataSource(messages);
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  clearFilters() {
    this.filterAndSortForm.controls.searchContent.setValue(null);
    this.filterAndSortForm.controls.deviceTypeFilter.setValue(null);
    this.searchMessages();
  }

  searchMessages($event?: PageEvent) {
    const searchContent = this.filterAndSortForm.controls.searchContent.value;
    const deviceTypeFilter =
      this.filterAndSortForm.controls.deviceTypeFilter.value;
    const pageSize = $event ? $event.pageSize : this.pageSize;
    const pageNumber = $event ? $event.pageIndex : 0;
    const filter =
      (searchContent ? searchContent : '') +
      ' ' +
      (deviceTypeFilter ? deviceTypeFilter : '');
    this.store.dispatch(
      searchPropertyMessages({
        filter,
        propertyId: this.propertyId,
        pageNumber,
        pageSize,
      })
    );
  }

  preventClose($event: MouseEvent) {
    $event.stopPropagation();
  }
}
