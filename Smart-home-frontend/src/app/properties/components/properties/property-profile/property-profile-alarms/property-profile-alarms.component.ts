import { Component, Input } from '@angular/core';
import { Device } from '../../../../../shared/model/device.model';

@Component({
  selector: 'app-property-profile-alarms',
  templateUrl: './property-profile-alarms.component.html',
  styleUrls: ['./property-profile-alarms.component.scss'],
})
export class PropertyProfileAlarmsComponent {
  @Input() devices!: Device[];
}
