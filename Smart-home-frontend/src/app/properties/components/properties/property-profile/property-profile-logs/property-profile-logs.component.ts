import { Component, OnInit, ViewChild } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { User } from "../../../../../shared/model/user.model";
import { Subscription } from "rxjs";
import { FormControl, FormGroup } from "@angular/forms";
import { MatPaginator } from "@angular/material/paginator";
import { Store } from "@ngrx/store";
import { selectUserPage } from "../../../../../users/store/users.selectors";
import { LogResponse } from "../../../../model/log-response";

@Component({
  selector: 'app-property-profile-logs',
  templateUrl: './property-profile-logs.component.html',
  styleUrls: ['./property-profile-logs.component.scss']
})
export class PropertyProfileLogsComponent  implements OnInit{
  displayedColumns = ['message', 'createdAt'];
  dataSource!: MatTableDataSource<LogResponse>;
  storeSubscription!: Subscription;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  searchForm!: FormGroup<{
    search: FormControl;
  }>;
  totalElements = 0;
  pageSize = 10;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      search: new FormControl(null),
    });
    // this.storeSubscription = this.store
    //   .select(selectUserPage)
    //   .subscribe((userPage) => {
    //     let users: User[] = [];
    //     if (userPage) {
    //       users = userPage.items;
    //       this.totalElements = userPage.totalElements;
    //       this.pageSize = userPage.pageSize;
    //       if (this.paginator) {
    //         this.paginator.pageIndex = userPage.pageNumber;
    //       }
    //     }
    //     this.dataSource = new MatTableDataSource(users);
    //   });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  preventClose($event: MouseEvent) {
    $event.stopPropagation();
  }

  clearFilters() {
    this.searchForm.controls.search.setValue(null);
 //   this.searchUsers();
  }


}
