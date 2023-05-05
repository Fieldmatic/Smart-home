import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordMatchValidator } from '../../../validators/password-match.validator';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { changePassword } from '../../../store/password.actions';

@Component({
  selector: 'app-set-password-form',
  templateUrl: './set-password-form.component.html',
  styleUrls: ['./set-password-form.component.scss'],
})
export class SetPasswordFormComponent implements OnInit {
  passwordForm!: FormGroup;
  hidePassword = true;
  hidePasswordConfirmation = true;

  constructor(private route: ActivatedRoute, private store: Store) {}

  ngOnInit() {
    this.passwordForm = new FormGroup({
      newPassword: new FormControl(null, [
        Validators.required,
        Validators.pattern(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{12,32}$/
        ),
      ]),
      newPasswordConfirmation: new FormControl(null, [
        Validators.required,
        passwordMatchValidator,
      ]),
    });
    this.passwordForm.get('newPassword')?.valueChanges.subscribe(() => {
      this.passwordForm
        .get('newPasswordConfirmation')
        ?.updateValueAndValidity();
    });
  }

  setPassword() {
    if (this.passwordForm.valid) {
      const newPassword = this.passwordForm.controls['newPassword'].value;
      const newPasswordConfirmation =
        this.passwordForm.controls['newPasswordConfirmation'].value;
      const authToken = this.route.snapshot.params['authToken'];
      this.store.dispatch(
        changePassword({ newPassword, newPasswordConfirmation, authToken })
      );
    }
  }

  toggleShowPassword($event: MouseEvent) {
    $event.preventDefault();
    this.hidePassword = !this.hidePassword;
  }

  toggleShowPasswordConfirmation($event: MouseEvent) {
    $event.preventDefault();
    this.hidePasswordConfirmation = !this.hidePasswordConfirmation;
  }
}
