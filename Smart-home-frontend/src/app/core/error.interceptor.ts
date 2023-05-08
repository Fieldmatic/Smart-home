import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { NotifierService } from './notifier.service';
import { Store } from '@ngrx/store';
import { logoutSuccess } from '../auth/store/auth.actions';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private notifierService: NotifierService, private store: Store) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        console.log(error);
        if (error.error?.message === "User fingerprint hash doesn't match!") {
          this.store.dispatch(logoutSuccess());
        }
        this.notifierService.notifyError(error);
        return throwError(error);
      })
    );
  }
}
