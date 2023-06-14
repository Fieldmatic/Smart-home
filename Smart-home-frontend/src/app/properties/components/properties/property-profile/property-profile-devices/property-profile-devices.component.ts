import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { MatDialog } from '@angular/material/dialog';
import { Device } from '../../../../../shared/model/device.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { addPropertyDevice } from '../../../../store/properties.actions';
import { AddPropertyDialogComponent } from './add-property-device-dialog/add-property-dialog.component';

@Component({
  selector: 'app-propery-profile-devices',
  templateUrl: './property-profile-devices.component.html',
  styleUrls: ['./property-profile-devices.component.scss'],
})
export class PropertyProfileDevicesComponent {
  @Input() devices!: Device[];
  @Input() propertyId!: string;

  newDeviceForm = new FormGroup({
    name: new FormControl('', Validators.required),
  });

  constructor(private store: Store, private dialog: MatDialog) {}

  addDevice() {
    const name = this.newDeviceForm.controls['name'].value;
    if (name) {
      const dialogRef = this.dialog.open(AddPropertyDialogComponent, {
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
