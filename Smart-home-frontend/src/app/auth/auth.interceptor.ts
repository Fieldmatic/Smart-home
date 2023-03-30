import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectToken } from './store/auth.selectors';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private store: Store) {}

  intercept(
    request: HttpRequest<never>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    if (request.headers.get('skip')) {
      request = request.clone({
        headers: request.headers.delete('skip'),
      });
      return next.handle(request);
    }
    return this.store.select(selectToken).pipe(
      switchMap((token) => {
        if (!token) {
          return next.handle(request);
        }
        const modifiedReq = request.clone({
          headers: new HttpHeaders({ Authorization: 'Bearer ' + token.token }),
        });
        return next.handle(modifiedReq);
      })
    );
  }
}
