import {DeviceType} from "../../shared/model/device-type";
import {Device} from "../../shared/model/device.model";

export function getDeviceIcon(device: Device): string {
  switch (device.deviceType) {
    case DeviceType.THERMOMETER:
      return 'thermostat';
    case DeviceType.DOOR:
      return 'door';
    case DeviceType.CAMERA:
      return 'camera';
    case DeviceType.LIGHT:
      return 'light';
    case DeviceType.BAROMETER:
      return 'pressure';
    default:
      return 'devices';
  }
}
