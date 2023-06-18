import { DeviceType } from "./device-type";
import {Alarm} from "./alarm.model";
import {DeviceRule} from "./device-rule";

export class Device {
  constructor(public uuid: string, public name: string, public deviceType: DeviceType, public messageRegex: string, public readPeriod: number, public alarms: Alarm[], public deviceRule: DeviceRule) {}
}
