import { Component, OnDestroy, OnInit } from '@angular/core';
import { selectRole } from '../../auth/store/auth.selectors';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { logout } from '../../auth/store/auth.actions';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  showMobileMenu = false;
  storeSubscription!: Subscription;
  userRole: string | null = null;

  constructor(private store: Store) {}

  toggleMobileMenu() {
    this.showMobileMenu = !this.showMobileMenu;
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.storeSubscription = this.store
      .select(selectRole)
      .subscribe((role) => (this.userRole = role));
  }

  logout() {
    this.store.dispatch(logout());
  }
}
