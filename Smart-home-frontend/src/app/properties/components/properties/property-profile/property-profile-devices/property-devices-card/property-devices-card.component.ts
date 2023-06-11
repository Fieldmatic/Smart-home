import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../../../shared/model/user.model";
import {Device} from "../../../../../../shared/model/device.model";
import {DeviceType} from "../../../../../../shared/model/device-type";

@Component({
  selector: 'app-property-devices-card',
  templateUrl: './property-devices-card.component.html',
  styleUrls: ['./property-devices-card.component.scss']
})
export class PropertyDevicesCardComponent {
  @Input() device!: Device;

  getDeviceIcon(): string {
    if (this.device.deviceType === DeviceType.THERMOMETER) {
      return 'thermostat';
    } else if (this.device.deviceType == DeviceType.DOOR) {
      return 'door';
    } else if (this.device.deviceType == DeviceType.CAMERA) {
      return 'camera';
    } else if (this.device.deviceType == DeviceType.LIGHT) {
      return 'light';
    } else {
      return 'devices';
    }
  }
}
