import {Component, Input} from '@angular/core';
import {User} from "../../../../../shared/model/user.model";
import {Store} from "@ngrx/store";
import {MatDialog} from "@angular/material/dialog";
import {Device} from "../../../../../shared/model/device.model";

@Component({
  selector: 'app-propery-profile-devices',
  templateUrl: './propery-profile-devices.component.html',
  styleUrls: ['./propery-profile-devices.component.scss']
})
export class ProperyProfileDevicesComponent {
  @Input() devices!: Device[];


  constructor(private store: Store, private dialog: MatDialog) {
  }
}
