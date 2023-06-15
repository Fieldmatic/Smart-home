import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Device} from "../../../../../shared/model/device.model";

@Component({
  selector: 'app-property-profile-alarms',
  templateUrl: './property-profile-alarms.component.html',
  styleUrls: ['./property-profile-alarms.component.scss']
})
export class PropertyProfileAlarmsComponent {
  @Input() devices!: Device[];

  newDeviceForm = new FormGroup({
    name: new FormControl('', Validators.required),
  });


}
