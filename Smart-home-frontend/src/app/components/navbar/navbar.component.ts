import { Component, OnDestroy, OnInit } from '@angular/core';
import { selectRole } from '../../auth/store/auth.selectors';
import { Store } from '@ngrx/store';
import { filter, Subscription } from 'rxjs';
import { logout } from '../../auth/store/auth.actions';
import { NavigationEnd, Router } from '@angular/router';
import { StompService } from "../../stomp.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  showMobileMenu = false;
  storeSubscription!: Subscription;
  userRole: string | null = null;
  welcomingPageIsActive = false;
  websocketByRoleConnected = false;

  constructor(private store: Store, private router: Router, private stompService: StompService) {}

  toggleMobileMenu() {
    this.showMobileMenu = !this.showMobileMenu;
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event) => {
        this.welcomingPageIsActive =
          (<NavigationEnd>event).url === '/' && !this.userRole;
      });
    this.storeSubscription = this.store
      .select(selectRole)
      .subscribe((role) => {
        this.userRole = role
        if (this.userRole === 'ADMIN' && !this.websocketByRoleConnected) {
            this.subscribeOnWebSocketAsAdmin();
        }
      });
  }

  logout() {
    this.store.dispatch(logout());
  }

  subscribeOnWebSocketAsAdmin() {
    const stompClient = this.stompService.connect();
    this.websocketByRoleConnected = true;
    stompClient.connect({}, () => {
      stompClient.subscribe('/topic/admin', (response): any => {
        console.log(response.body)
      });
    });
  }
}
