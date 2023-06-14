import { Component, Input } from '@angular/core';
import { Device } from '../../model/device.model';
import { DeviceType } from '../../model/device-type';

@Component({
  selector: 'app-property-devices-card',
  templateUrl: './property-devices-card.component.html',
  styleUrls: ['./property-devices-card.component.scss'],
})
export class PropertyDevicesCardComponent {
  @Input() device!: Device;
  @Input() isAdmin!: boolean;

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
