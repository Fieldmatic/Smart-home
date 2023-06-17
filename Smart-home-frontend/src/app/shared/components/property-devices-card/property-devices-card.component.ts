import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { MatDialog } from '@angular/material/dialog';
import { Device } from '../../model/device.model';
import { AddDeviceRuleDialogComponent } from '../../../properties/components/properties/property-profile/property-profile-devices/add-device-rule-dialog/add-device-rule-dialog.component';
import { addDeviceRule } from '../../../properties/store/properties.actions';
import { getDeviceIcon } from '../../../properties/functions/getDeviceIcon';
import { DeviceType } from '../../model/device-type';

@Component({
  selector: 'app-property-devices-card',
  templateUrl: './property-devices-card.component.html',
  styleUrls: ['./property-devices-card.component.scss'],
})
export class PropertyDevicesCardComponent {
  @Input() device!: Device;
  @Input() isAdmin!: boolean;
  @Input() propertyId!: string;

  constructor(private store: Store, private dialog: MatDialog) {}

  openRuleDialog() {
    const dialogRef = this.dialog.open(AddDeviceRuleDialogComponent, {
      data: {
        device: this.device,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.store.dispatch(
          addDeviceRule({
            propertyId: this.propertyId,
            deviceId: this.device.uuid,
            minValue: result.minValue,
            maxValue: result.maxValue,
          })
        );
      }
    });
  }

  protected readonly getDeviceIcon = getDeviceIcon;
  protected readonly DeviceType = DeviceType;
}
