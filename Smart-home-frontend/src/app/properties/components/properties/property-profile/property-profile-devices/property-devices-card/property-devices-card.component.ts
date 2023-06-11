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
    switch (this.device.deviceType) {
      case DeviceType.THERMOMETER:
        return 'thermostat';
      case DeviceType.DOOR:
        return 'door';
      case DeviceType.CAMERA:
        return 'camera';
      case DeviceType.LIGHT:
        return 'light';
      default:
        return 'devices';
    }
  }
}
