import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormNavigationStep } from '../../../../shared/model/form-navigation-step.model';
import { Store } from '@ngrx/store';
import { createCSR } from '../../../store/certificates.actions';
import { textValidator } from '../../../../shared/validators/text.validator';

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
  algorithms = ['RSA', 'EC'];
  keySizes: { [key: string]: string[] } = {
    RSA: ['2048', '4096'],
    EC: ['256', '384', '521'],
  };

  constructor(private store: Store) {}

  get selectedAlgorithm(): string {
    return this.newCSRForm.controls['algorithm'].value;
  }

  ngOnInit(): void {
    this.newCSRForm = new FormGroup({
      common_name: new FormControl(null, [Validators.required, textValidator]),
      organization: new FormControl(null, [textValidator]),
      organizational_unit: new FormControl(null, [textValidator]),
      city: new FormControl(null, [textValidator]),
      state: new FormControl(null, [textValidator]),
      country: new FormControl(null, [textValidator]),
      algorithm: new FormControl('RSA', [Validators.required]),
      key_size: new FormControl(null, [Validators.required]),
    });
    this.newCSRForm.valueChanges.subscribe(() => {
      this.steps[0].filled =
        this.newCSRForm.controls['common_name'].valid &&
        this.newCSRForm.controls['organization'].valid &&
        this.newCSRForm.controls['organizational_unit'].valid &&
        this.newCSRForm.controls['city'].valid &&
        this.newCSRForm.controls['state'].valid &&
        this.newCSRForm.controls['country'].valid;
    });
  }

  createCSR() {
    if (this.newCSRForm.valid) {
      const commonName = this.newCSRForm.controls['common_name'].value;
      const organization = this.newCSRForm.controls['organization'].value;
      const organizationalUnit =
        this.newCSRForm.controls['organizational_unit'].value;
      const city = this.newCSRForm.controls['city'].value;
      const state = this.newCSRForm.controls['state'].value;
      const country = this.newCSRForm.controls['country'].value;
      const keySize = +this.newCSRForm.controls['key_size'].value;
      const algorithm = this.newCSRForm.controls['algorithm'].value;
      this.store.dispatch(
        createCSR({
          commonName,
          organization,
          organizationalUnit,
          city,
          state,
          country,
          keySize,
          algorithm,
        })
      );
    }
  }

  stepChanged(stepId: number) {
    this.visibleSectionId = stepId + 1;
  }
}
