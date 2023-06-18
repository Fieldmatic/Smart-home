import { Component, Input } from '@angular/core';
import { Device } from '../../../../../../shared/model/device.model';
import { Alarm } from '../../../../../../shared/model/alarm.model';
import { getDeviceIcon } from '../../../../../functions/getDeviceIcon';
import {AlarmType} from "../../../../../../shared/model/alarm-type";

@Component({
  selector: 'app-property-alarms-card',
  templateUrl: './property-alarms-card.component.html',
  styleUrls: ['./property-alarms-card.component.scss'],
})
export class PropertyAlarmsCardComponent {
  @Input() device!: Device;
  @Input() alarm!: Alarm;

  protected readonly getDeviceIcon = getDeviceIcon;

  protected readonly Number = Number;
  protected readonly AlarmType = AlarmType;
}
