import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { signUp } from '../../../store/auth.actions';
import { Store } from '@ngrx/store';
import { passwordValidator } from '../../../../shared/validators/password.validator';
import { userTypeValidator } from '../../../../shared/validators/user-type.validator';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss'],
})
export class SignupFormComponent implements OnInit {
  hidePassword = true;
  signUpForm!: FormGroup;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required, passwordValidator]),
      role: new FormControl('TENANT', [Validators.required, userTypeValidator]),
    });
  }

  toggleShowPassword(event: Event) {
    event.preventDefault();
    this.hidePassword = !this.hidePassword;
  }

  signUp() {
    if (this.signUpForm.valid) {
      const email = this.signUpForm.controls['email'].value;
      const password = this.signUpForm.controls['password'].value;
      const role = this.signUpForm.controls['role'].value;
      this.store.dispatch(signUp({ email, password, role }));
    }
  }
}
