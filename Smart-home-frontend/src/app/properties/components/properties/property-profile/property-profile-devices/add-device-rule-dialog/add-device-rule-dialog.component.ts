import {Component, Inject, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Store} from "@ngrx/store";
import {Device} from "../../../../../../shared/model/device.model";
import {DeviceType} from "../../../../../../shared/model/device-type";

@Component({
  selector: 'app-add-device-rule-dialog',
  templateUrl: './add-device-rule-dialog.component.html',
  styleUrls: ['./add-device-rule-dialog.component.scss']
})
export class AddDeviceRuleDialogComponent {
  dialogForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public data: { device: Device }
  ) {
    this.dialogForm = this.formBuilder.group({
      minValue: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      maxValue: ['', [Validators.required, Validators.pattern('^[0-9]+$')]]
    });
  }

  protected readonly DeviceType = DeviceType;
}
