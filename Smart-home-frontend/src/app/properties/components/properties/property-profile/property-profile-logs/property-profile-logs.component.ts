import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { FormControl, FormGroup } from "@angular/forms";
import { MatPaginator, PageEvent } from "@angular/material/paginator";
import { Store } from "@ngrx/store";
import { LogResponse } from "../../../../model/log-response";
import { PageResponse } from "../../../../../shared/model/page-response";
import { getLogsForProperty } from "../../../../store/properties.actions";

@Component({
  selector: 'app-property-profile-logs',
  templateUrl: './property-profile-logs.component.html',
  styleUrls: ['./property-profile-logs.component.scss']
})
export class PropertyProfileLogsComponent  implements OnInit, OnChanges{
  @Input() logPage!: PageResponse<LogResponse>;
  @Input() propertyId: string;

  searchForm!: FormGroup<{
    search: FormControl;
  }>;

  displayedColumns = ['message', 'createdAt'];
  dataSource!: MatTableDataSource<LogResponse>;
  totalElements = 0;
  pageSize = 10;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      search: new FormControl(null),
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.logPage) {
      this.totalElements = this.logPage.totalElements;
      this.pageSize = this.logPage.pageSize;
      if (this.paginator) {
        this.paginator.pageIndex = this.logPage.pageNumber;
      }
    }
    this.dataSource = new MatTableDataSource(this.logPage.items);
  }

  searchLogs($event?: PageEvent) {
    const search = this.searchForm.controls.search.value;
    const pageSize = $event ? $event.pageSize : this.pageSize;
    const pageNumber = $event ? $event.pageIndex : 0;
    const id = this.propertyId;
    this.store.dispatch(
      getLogsForProperty({
        id,
        pageSize,
        pageNumber,
        search,
      })
    );
  }

  clearFilters() {
    this.searchForm.controls.search.setValue(null);
    this.searchLogs();
  }
}
