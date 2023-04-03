import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SharedModule } from './shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from './core/core.module';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FooterComponent } from './components/footer/footer.component';
import { ToastrModule } from 'ngx-toastr';
import { WelcomingPageComponent } from './components/home-page/welcoming-page/welcoming-page.component';
import { AdminHomePageComponent } from './components/home-page/admin-home-page/admin-home-page.component';
import { UserHomePageComponent } from './components/home-page/user-home-page/user-home-page.component';
import { UserHomePageNoCsrComponent } from './components/home-page/user-home-page/user-home-page-no-csr/user-home-page-no-csr.component';
import { UserHomePagePendingCsrComponent } from './components/home-page/user-home-page/user-home-page-pending-csr/user-home-page-pending-csr.component';
import { UserHomePageCsrComponent } from './components/home-page/user-home-page/user-home-page-csr/user-home-page-csr.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomePageComponent,
    FooterComponent,
    WelcomingPageComponent,
    AdminHomePageComponent,
    UserHomePageComponent,
    UserHomePageNoCsrComponent,
    UserHomePagePendingCsrComponent,
    UserHomePageCsrComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      progressBar: true,
      preventDuplicates: true,
      closeButton: true,
    }),
    HttpClientModule,
    SharedModule,
    CoreModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
