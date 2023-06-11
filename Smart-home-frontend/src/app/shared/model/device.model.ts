import { DeviceType } from "./device-type";

export class Device {
  constructor(public name: string, public deviceType: DeviceType, public messageRegex: string) {}
}
