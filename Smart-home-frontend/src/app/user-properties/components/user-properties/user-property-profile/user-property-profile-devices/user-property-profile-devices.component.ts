import { Component, Input } from '@angular/core';
import { Device } from '../../../../../shared/model/device.model';

@Component({
  selector: 'app-user-property-profile-devices',
  templateUrl: './user-property-profile-devices.component.html',
  styleUrls: ['./user-property-profile-devices.component.scss'],
})
export class UserPropertyProfileDevicesComponent {
  @Input() devices: Device[] = [];
}
