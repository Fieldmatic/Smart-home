import {AlarmType} from "./alarm-type";

export class Alarm {
  constructor(public alarmType: AlarmType, public value: number, public time: Date, public userEmail: string, public errorMessage: string) {}
}
