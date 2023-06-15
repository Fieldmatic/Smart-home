
import {Component, Input} from '@angular/core';
import {Device} from "../../../../../../shared/model/device.model";
import {DeviceType} from "../../../../../../shared/model/device-type";
import {getDeviceIcon} from "../../../../../functions/getDeviceIcon";
import {addDeviceRule} from "../../../../../store/properties.actions";
import {Store} from "@ngrx/store";
import {MatDialog} from "@angular/material/dialog";
import {AddDeviceRuleDialogComponent} from "../add-device-rule-dialog/add-device-rule-dialog.component";

@Component({
  selector: 'app-property-devices-card',
  templateUrl: './property-devices-card.component.html',
  styleUrls: ['./property-devices-card.component.scss'],
})
export class PropertyDevicesCardComponent {
  @Input() device!: Device;
  @Input() isAdmin!: boolean;
  @Input() propertyId!: string;

  protected readonly getDeviceIcon = getDeviceIcon;

  constructor(private store: Store, private dialog: MatDialog) {
  }

  openRuleDialog() {
      const dialogRef = this.dialog.open(AddDeviceRuleDialogComponent, {
        data: {
          device: this.device
        },
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          console.log(result)
          this.store.dispatch(
            addDeviceRule({ propertyId: this.propertyId, deviceId: this.device.uuid, minValue: result.minValue, maxValue: result.maxValue})
          );
        }
      });
    }

  protected readonly DeviceType = DeviceType;
}

