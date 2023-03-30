import { Component, OnDestroy, OnInit } from '@angular/core';
import { selectToken } from '../../auth/store/auth.selectors';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  showMobileMenu = false;
  storeSubscription!: Subscription;
  userRole: string | undefined;

  constructor(private store: Store) {}

  toggleMobileMenu() {
    this.showMobileMenu = !this.showMobileMenu;
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectToken)
      .subscribe((token) => (this.userRole = token?.token));
  }
}
