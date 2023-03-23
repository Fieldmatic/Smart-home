import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  showMobileMenu = false;

  toggleMobileMenu() {
    this.showMobileMenu = !this.showMobileMenu;
  }
}
