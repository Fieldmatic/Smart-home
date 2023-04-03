import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { autoLogin } from './auth/store/auth.actions';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'Smart Home';

  constructor(
    private store: Store,
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    matIconRegistry.addSvgIcon(
      'hidePassword',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/password_visibility/visibility_off.svg'
      )
    );
    matIconRegistry.addSvgIcon(
      'showPassword',
      this.domSanitizer.bypassSecurityTrustResourceUrl(
        '../assets/password_visibility/visibility.svg'
      )
    );
  }

  ngOnInit(): void {
    this.store.dispatch(autoLogin());
  }
}
