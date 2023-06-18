import {Component, Input} from '@angular/core';
import {Device} from "../../../../../shared/model/device.model";

@Component({
  selector: 'app-user-property-profile-alarms',
  templateUrl: './user-property-profile-alarms.component.html',
  styleUrls: ['./user-property-profile-alarms.component.scss']
})
export class UserPropertyProfileAlarmsComponent {
  @Input() devices!: Device[];


}
