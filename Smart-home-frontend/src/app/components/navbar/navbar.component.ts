import { Component, OnDestroy, OnInit } from '@angular/core';
import {selectDecodedToken, selectRole} from '../../auth/store/auth.selectors';
import { Store } from '@ngrx/store';
import { filter, Subscription } from 'rxjs';
import { logout } from '../../auth/store/auth.actions';
import { NavigationEnd, Router } from '@angular/router';
import { StompService } from "../../stomp.service";
import {NotifierService} from "../../core/notifier.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  showMobileMenu = false;
  storeSubscription!: Subscription;
  userRole: string | null = null;
  userEmail: string | null = null;
  welcomingPageIsActive = false;
  websocketByRoleConnected = false;

  constructor(private store: Store, private router: Router, private stompService: StompService, private notifierService: NotifierService) {}

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
      .select(selectDecodedToken)
      .subscribe((jwt) => {
        this.userEmail = jwt.sub;
        this.userRole = jwt.role;
        if (this.userRole === 'ADMIN') {
          this.subscribeOnWebSocket("admin");
        } else {
          this.subscribeOnWebSocket("user/" + this.userEmail)
        }
      });
  }

  logout() {
    this.store.dispatch(logout());
  }

  subscribeOnWebSocket(role: string) {
    if (!this.websocketByRoleConnected) {
      const stompClient = this.stompService.connect();
      this.websocketByRoleConnected = true;
      stompClient.connect({}, () => {
        stompClient.subscribe('/topic/' + role, (response): any => {
          this.notifierService.notifyWarn(response.body)
        });
      });
    }
  }
}
