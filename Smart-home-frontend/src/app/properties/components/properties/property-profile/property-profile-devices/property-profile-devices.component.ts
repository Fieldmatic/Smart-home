import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { MatDialog } from '@angular/material/dialog';
import { Device } from '../../../../../shared/model/device.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { addPropertyDevice } from '../../../../store/properties.actions';
import { textValidator } from '../../../../../shared/validators/text.validator';
import {AddDeviceToPropertyDialogComponent} from "./add-property-device-dialog/add-device-to-property-dialog.component";

@Component({
  selector: 'app-property-profile-devices',
  templateUrl: './property-profile-devices.component.html',
  styleUrls: ['./property-profile-devices.component.scss'],
})
export class PropertyProfileDevicesComponent {
  @Input() devices!: Device[];
  @Input() propertyId!: string;

  newDeviceForm = new FormGroup({
    name: new FormControl('', [Validators.required, textValidator]),
  });

  constructor(private store: Store, private dialog: MatDialog) {}

  addDevice() {
    const name = this.newDeviceForm.controls['name'].value;
    if (name) {
      const dialogRef = this.dialog.open(AddDeviceToPropertyDialogComponent, {
        data: {
          title: 'New Property Device',
          name: name,
          text: `Please enter the required information about the device`,
        },
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          this.store.dispatch(
            addPropertyDevice({
              propertyId: this.propertyId,
              name: name || '',
              deviceType: result.deviceType,
              readPeriod: result.readPeriod,
              messageRegex: result.messageRegex,
            })
          );
        }
      });
    }
  }
}
