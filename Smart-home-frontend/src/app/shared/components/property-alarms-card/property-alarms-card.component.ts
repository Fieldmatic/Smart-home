import { Component, Input } from '@angular/core';
import { Device } from '../../model/device.model';
import { Alarm } from '../../model/alarm.model';
import { getDeviceIcon } from '../../../properties/functions/getDeviceIcon';
import {AlarmType} from "../../model/alarm-type";

@Component({
  selector: 'app-property-alarms-card',
  templateUrl: './property-alarms-card.component.html',
  styleUrls: ['./property-alarms-card.component.scss'],
})
export class PropertyAlarmsCardComponent {
  @Input() device!: Device;
  @Input() alarm!: Alarm;
  @Input() isAdmin!: boolean;

  protected readonly getDeviceIcon = getDeviceIcon;

  protected readonly Number = Number;
  protected readonly AlarmType = AlarmType;
}
