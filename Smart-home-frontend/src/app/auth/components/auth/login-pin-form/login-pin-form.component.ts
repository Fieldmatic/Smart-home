import { Component, OnDestroy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { loginFirstStep, loginSecondStep } from '../../../store/auth.actions';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { selectAuth } from '../../../store/auth.selectors';

@Component({
  selector: 'app-login-pin-form',
  templateUrl: './login-pin-form.component.html',
  styleUrls: ['./login-pin-form.component.scss'],
})
export class LoginPinFormComponent implements OnDestroy, OnInit {
  pinForm!: FormGroup;
  storeSubscription!: Subscription;
  userTryingToLogin!: { email: string; password: string };
  pinExpiresIn!: number;
  hasPinExpired = false;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.pinForm = new FormGroup({
      pin: new FormControl(null, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(4),
        Validators.pattern('[0-9]*'),
      ]),
    });
    this.storeSubscription = this.store
      .select(selectAuth)
      .subscribe((state) => {
        const userTryingToLogin = state.userTryingToLogin;
        const pinExpiresIn = state.pinValidityTime;
        if (userTryingToLogin) {
          this.userTryingToLogin = userTryingToLogin;
        }
        if (pinExpiresIn) {
          this.pinExpiresIn = pinExpiresIn;
        }
      });
  }

  ngOnDestroy(): void {
    this.storeSubscription.unsubscribe();
  }

  submitPin() {
    if (this.pinForm.valid) {
      this.store.dispatch(
        loginSecondStep({
          email: this.userTryingToLogin.email,
          password: this.userTryingToLogin.password,
          pin: this.pinForm.controls['pin'].value,
        })
      );
    }
  }

  pinExpired() {
    this.hasPinExpired = true;
    this.pinExpiresIn = 0;
  }

  resendPIN($event: MouseEvent) {
    $event.preventDefault();
    this.store.dispatch(
      loginFirstStep({
        email: this.userTryingToLogin.email,
        password: this.userTryingToLogin.password,
      })
    );
    this.hasPinExpired = false;
  }
}
