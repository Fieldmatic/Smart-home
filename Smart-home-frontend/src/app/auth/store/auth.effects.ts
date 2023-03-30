import { Injectable } from '@angular/core';
import { AuthHttpService } from '../services/auth-http.service';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import * as AuthActions from './auth.actions';
import { map, switchMap, tap } from 'rxjs';
import { Router } from '@angular/router';
import { NotifierService } from '../../core/notifier.service';

@Injectable()
export class AuthEffects {
  login = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.login.type),
      switchMap((action) => {
        return this.httpService
          .sendLoginRequest(action.email, action.password)
          .pipe(map((authToken) => AuthActions.login_success(authToken)));
      })
    );
  });

  login_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(AuthActions.login_success.type),
        tap(() => {
          this.router.navigate(['/']);
        }),
        map((action) => {
          sessionStorage.setItem('token', action.token);
          sessionStorage.setItem('role', action.role);
        })
      );
    },
    { dispatch: false }
  );

  sign_up = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.sign_up.type),
      switchMap((action) => {
        return this.httpService
          .sendSignUpRequest(action.email, action.password, action.role)
          .pipe(map(() => AuthActions.sign_up_success()));
      })
    );
  });

  sign_up_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(AuthActions.sign_up_success.type),
        map(() => {
          const message =
            'Your sign up request has been sent. Please go check your email.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  confirm_email = createEffect(() => {
    return this.actions$.pipe(
      ofType(AuthActions.confirm_email.type),
      switchMap((action) => {
        return this.httpService
          .sendEmailConfirmationRequest(action.token)
          .pipe(map(() => AuthActions.confirm_email_success()));
      })
    );
  });

  confirm_email_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(AuthActions.confirm_email_success.type),
        map(() => {
          const message =
            'You have successfully confirmed your email address. You may log in now.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<AuthActions.AuthActionsUnion>,
    private httpService: AuthHttpService
  ) {}
}
