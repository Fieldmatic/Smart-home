import {Component, Input} from '@angular/core';
import {User} from "../../../../../shared/model/user.model";
import {Device} from "../../../../../shared/model/device.model";

@Component({
  selector: 'app-property-devices-card',
  templateUrl: './property-devices-card.component.html',
  styleUrls: ['./property-devices-card.component.scss']
})
export class PropertyDevicesCardComponent {
  @Input() device!: Device;


}
