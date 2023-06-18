import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectCertificateStatus } from '../../../auth/store/auth.selectors';

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.scss'],
})
export class UserHomePageComponent implements OnInit, OnDestroy {
  private storeSubscription!: Subscription;
  userCertificateStatus: string | null = null;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectCertificateStatus)
      .subscribe((certificateStatus) => {
        this.userCertificateStatus = certificateStatus;
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription?.unsubscribe();
  }
}
