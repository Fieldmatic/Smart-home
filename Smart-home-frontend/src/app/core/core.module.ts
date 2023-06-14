import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_CONFIG, APP_SERVICE_CONFIG } from '../app-config/app-config';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../../environments/environment.prod';
import { AuthInterceptor } from '../auth/auth.interceptor';
import { ErrorInterceptor } from './error.interceptor';
import { reducers } from './store/core.reducer';

@NgModule({
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true,
    },
  ],
  imports: [
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot(),
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
  ],
  exports: [StoreModule, EffectsModule, StoreDevtoolsModule],
})
export class CoreModule {}
