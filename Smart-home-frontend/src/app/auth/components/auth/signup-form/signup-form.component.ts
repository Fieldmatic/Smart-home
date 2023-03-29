import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.scss'],
})
export class SignupFormComponent implements OnInit {
  hidePassword = true;
  signUpForm!: FormGroup;

  constructor(
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
    this.signUpForm = new FormGroup({
      email: new FormControl(null, [
        Validators.required,
        Validators.email,
        Validators.minLength(6),
      ]),
      password: new FormControl(null, [Validators.required]),
      role: new FormControl('tenant', [Validators.required]),
    });
  }

  toggleShowPassword(event: Event) {
    event.preventDefault();
    this.hidePassword = !this.hidePassword;
  }

  signUp() {
    console.log('SIGN UP!');
  }
}
