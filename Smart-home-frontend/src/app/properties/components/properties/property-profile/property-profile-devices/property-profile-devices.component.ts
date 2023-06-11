import {Component, Input} from '@angular/core';
import {Store} from "@ngrx/store";
import {MatDialog} from "@angular/material/dialog";
import {Device} from "../../../../../shared/model/device.model";
import { FormControl, FormGroup } from "@angular/forms";
import {
  ConfirmationDialogComponent
} from "../../../../../shared/components/confirmation-dialog/confirmation-dialog.component";
import { AddPropertyDialogComponent } from "./add-property-dialog/add-property-dialog.component";

@Component({
  selector: 'app-propery-profile-devices',
  templateUrl: './property-profile-devices.component.html',
  styleUrls: ['./property-profile-devices.component.scss']
})
export class PropertyProfileDevicesComponent {
  @Input() devices!: Device[];
  newDeviceForm = new FormGroup({
    name: new FormControl(''),
  });

  constructor(private store: Store, private dialog: MatDialog) {
  }

  addDevice() {
    const name = this.newDeviceForm.controls['name'].value;
    if (name) {
      const dialogRef = this.dialog.open(AddPropertyDialogComponent, {
        data: {
          title: 'New Property Device',
          name: this.newDeviceForm.controls.name.value,
          text: `Are you sure you want to add a new device to the property?`,
        },
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          // this.store.dispatch(
          //   addPropertyMember({ propertyId: this.propertyId, userId: id })
          // );
          console.log(result)
        }
      });
    }
  }
}
