import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormNavigationStep } from '../../../../shared/model/form-navigation-step.model';

@Component({
  selector: 'app-new-csr-form',
  templateUrl: './new-csr-form.component.html',
  styleUrls: ['./new-csr-form.component.scss'],
})
export class NewCsrFormComponent implements OnInit {
  newCSRForm!: FormGroup;
  steps: FormNavigationStep[] = [
    { label: 'Organization Info', active: true, filled: false },
    { label: 'Key Settings', active: false, filled: false },
  ];
  visibleSectionId = 1;

  ngOnInit(): void {
    this.newCSRForm = new FormGroup({
      email: new FormControl(null, [
        Validators.required,
        Validators.email,
        Validators.minLength(6),
      ]),
      organization: new FormControl(null, [Validators.required]),
      organizational_unit: new FormControl(null, [Validators.required]),
      city: new FormControl(null, [Validators.required]),
      state: new FormControl(null, [Validators.required]),
      country: new FormControl(null, [Validators.required]),
      key_size: new FormControl('4096', [Validators.required]),
      algorithm: new FormControl('RSA', [Validators.required]),
    });
    this.newCSRForm.valueChanges.subscribe(() => {
      this.steps[0].filled =
        this.newCSRForm.controls['email'].valid &&
        this.newCSRForm.controls['organization'].valid &&
        this.newCSRForm.controls['organizational_unit'].valid &&
        this.newCSRForm.controls['city'].valid &&
        this.newCSRForm.controls['state'].valid &&
        this.newCSRForm.controls['country'].valid;
    });
  }

  createCSR() {
    console.log('Create CSR');
  }

  stepChanged(stepId: number) {
    this.visibleSectionId = stepId + 1;
  }
}
