import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-property-dialog',
  templateUrl: './add-device-to-property-dialog.component.html',
  styleUrls: ['./add-device-to-property-dialog.component.scss'],
})
export class AddDeviceToPropertyDialogComponent {
  dialogForm: FormGroup;
  options: string[] = ['DOOR', 'CAMERA', 'THERMOMETER', 'LIGHT', 'BAROMETER'];

  constructor(
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public data: { name: string; title: string; text: string }
  ) {
    this.dialogForm = this.formBuilder.group({
      deviceType: ['', Validators.required],
      readPeriod: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      messageRegex: ['', Validators.required],
    });
  }
}
