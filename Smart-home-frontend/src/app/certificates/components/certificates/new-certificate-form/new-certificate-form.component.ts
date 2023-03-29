import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormNavigationStep } from '../../../../shared/model/form-navigation-step.model';

@Component({
  selector: 'app-new-certificate-form',
  templateUrl: './new-certificate-form.component.html',
  styleUrls: ['./new-certificate-form.component.scss'],
})
export class NewCertificateFormComponent implements OnInit {
  newCertificateForm!: FormGroup;
  validityPeriod = new FormGroup({
    start: new FormControl<Date | null>(null, [Validators.required]),
    end: new FormControl<Date | null>(null, [Validators.required]),
  });
  steps: FormNavigationStep[] = [
    { label: 'CSR Info', active: true, filled: true },
    { label: 'Basic Info', active: false, filled: true },
    { label: 'Extensions', active: false, filled: false },
  ];
  visibleSectionId = 1;

  ngOnInit(): void {
    this.newCertificateForm = new FormGroup({
      name: new FormControl(null, [Validators.required]),
      type: new FormControl('SSL_CLIENT', [Validators.required]),
      signature: new FormControl(false),
      non_repudiation: new FormControl(false),
      key_encipherment: new FormControl(false),
      data_encipherment: new FormControl(false),
      key_agreement: new FormControl(false),
      certificate_signing: new FormControl(false),
      crl_signing: new FormControl(false),
      encipher_only: new FormControl(false),
      decipher_only: new FormControl(false),
    });
  }

  createCertificate() {
    console.log('Create Certificate');
  }

  stepChanged(stepId: number) {
    this.visibleSectionId = stepId + 1;
  }
}
