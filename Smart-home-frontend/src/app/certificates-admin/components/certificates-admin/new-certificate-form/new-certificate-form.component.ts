import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { FormNavigationStep } from '../../../../shared/model/form-navigation-step.model';
import { Store } from '@ngrx/store';
import {
  selectCertificateExtensions,
  selectCertificateTypes,
  selectCSR,
} from '../../../store/certificates-admin.selectors';
import { ActivatedRoute } from '@angular/router';
import { CSR } from '../../../model/CSR.model';
import { CertificateType } from '../../../model/certificate-type.model';
import { Extension } from '../../../model/extension.model';
import { Capabilities } from '../../../model/capabilities.model';
import { create_certificate } from '../../../store/certificates-admin.actions';

@Component({
  selector: 'app-new-certificate-form',
  templateUrl: './new-certificate-form.component.html',
  styleUrls: ['./new-certificate-form.component.scss'],
})
export class NewCertificateFormComponent implements OnInit {
  newCertificateForm!: FormGroup;
  validityPeriod = new FormGroup({
    start: new FormControl<Date>(new Date(), [Validators.required]),
    end: new FormControl<Date>(new Date(), [Validators.required]),
  });
  extensionForms!: FormGroup;
  steps: FormNavigationStep[] = [
    { label: 'CSR Info', active: true, filled: true },
    { label: 'Basic Info', active: false, filled: false },
  ];
  visibleSectionId = 1;
  certificateTypes: CertificateType[] = [];
  certificateExtensions: Extension[] = [];
  csr: CSR | undefined;

  constructor(
    private store: Store,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  capabilitiesByCertificateType(certificateTypeName: string) {
    return this.certificateTypes.filter(
      (certificateType) => certificateType.name === certificateTypeName
    )[0].capabilities;
  }

  get selectedCapabilities(): Capabilities[] {
    const capabilities: Capabilities[] = [];
    Object.keys(this.extensionsFormGroups).forEach((extensionName) => {
      Object.keys(this.extensionsFormControls(extensionName)).forEach(
        (capabilityName) => {
          if (
            this.extensionsFormControls(extensionName)[capabilityName].value
          ) {
            capabilities.push(
              this.certificateExtensions
                .filter(
                  (extension) =>
                    extension.name === this.controlDisplayName(extensionName)
                )[0]
                .capabilities.filter(
                  (c) => c.name === this.controlDisplayName(capabilityName)
                )[0]
            );
          }
        }
      );
    });
    return capabilities;
  }

  get extensionsFormGroups(): { [key: string]: FormGroup } {
    return this.extensionForms.controls as { [key: string]: FormGroup };
  }

  extensionsFormControls(name: string): { [key: string]: FormControl } {
    return (<FormGroup>this.extensionForms.controls[name]).controls as {
      [key: string]: FormControl;
    };
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.store
      .select(selectCSR(id))
      .subscribe((c) => {
        this.csr = c;
      })
      .unsubscribe();
    this.store
      .select(selectCertificateTypes)
      .subscribe((certificateTypes) => {
        this.certificateTypes = certificateTypes;
      })
      .unsubscribe();
    this.store
      .select(selectCertificateExtensions)
      .subscribe((certificateExtensions) => {
        this.certificateExtensions = certificateExtensions;
      })
      .unsubscribe();
    this.newCertificateForm = new FormGroup({
      commonName: new FormControl({
        value: this.csr?.commonName,
        disabled: true,
      }),
      email: new FormControl({ value: this.csr?.email, disabled: true }),
      organization: new FormControl({
        value: this.csr?.organization,
        disabled: true,
      }),
      organizationalUnit: new FormControl({
        value: this.csr?.organizationalUnit,
        disabled: true,
      }),
      city: new FormControl({ value: this.csr?.city, disabled: true }),
      state: new FormControl({ value: this.csr?.state, disabled: true }),
      country: new FormControl({ value: this.csr?.country, disabled: true }),
      keySize: new FormControl({ value: this.csr?.keySize, disabled: true }),
      algorithm: new FormControl({
        value: this.csr?.algorithm,
        disabled: true,
      }),
      type: new FormControl('', [Validators.required]),
      serialNumber: new FormControl(1, [Validators.required]),
      caAlias: new FormControl('intermediate1', [Validators.required]),
    });
    this.initFormGroupForExtensions();
    this.connectControlsWithCertificateType();
    this.newCertificateForm.controls['type'].setValue(
      this.certificateTypes[0].name
    );
  }

  initFormGroupForExtensions() {
    const extensionFormGroups: { [key: string]: FormGroup } = {};
    this.certificateExtensions.forEach((extension) => {
      const extensionName = this.optimizeName(extension.name);
      const extensionFormControls: { [key: string]: FormControl } = {};
      extension.capabilities.forEach((capabilities) => {
        const capabilitiesName = this.optimizeName(capabilities.name);
        extensionFormControls[capabilitiesName] = new FormControl(false);
      });
      extensionFormGroups[extensionName] = this.formBuilder.group(
        extensionFormControls
      );
      this.steps.push({
        label: this.controlDisplayName(extensionName) + ' Extension',
        active: false,
        filled: true,
      });
    });
    this.extensionForms = this.formBuilder.group(extensionFormGroups);
  }

  connectControlsWithCertificateType() {
    this.newCertificateForm.controls['type'].valueChanges.subscribe(
      (certificateType) => {
        const capabilities =
          this.capabilitiesByCertificateType(certificateType);
        this.certificateExtensions.forEach((extension) => {
          Object.values(
            (<FormGroup>(
              this.extensionForms.controls[this.optimizeName(extension.name)]
            )).controls
          ).forEach((control) => {
            control.setValue(false);
          });
        });
        capabilities.forEach((c) => {
          this.certificateExtensions.forEach((extension) => {
            this.extensionsFormGroups[
              this.optimizeName(extension.name)
            ].controls[this.optimizeName(c.name)]?.setValue(true);
          });
        });
      }
    );
  }

  createCertificate() {
    if (this.newCertificateForm.valid && this.validityPeriod.valid) {
      const capabilities: Capabilities[] = this.selectedCapabilities;
      const start = this.validityPeriod.controls['start'].value;
      const end = this.validityPeriod.controls['end'].value;
      const csrId = this.csr?.id;
      const serialNumber =
        this.newCertificateForm.controls['serialNumber'].value + '';
      const caAlias = this.newCertificateForm.controls['caAlias'].value;
      if (start && end && csrId) {
        this.store.dispatch(
          create_certificate({
            capabilities,
            start,
            end,
            csrId,
            serialNumber,
            caAlias,
          })
        );
      }
    }
  }

  stepChanged(stepId: number) {
    this.steps[1].filled = this.validityPeriod.valid;
    this.visibleSectionId = stepId + 1;
  }

  controlDisplayName(controlName: string) {
    return controlName.replace(/_/g, ' ');
  }

  optimizeName(name: string) {
    return name.replace(/\s/g, '_');
  }

  extensionsMiddle(name: string) {
    return Math.ceil(Object.keys(this.extensionsFormControls(name)).length / 2);
  }
}
