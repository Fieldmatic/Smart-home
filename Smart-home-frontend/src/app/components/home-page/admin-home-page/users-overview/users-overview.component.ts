import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-users-overview',
  templateUrl: './users-overview.component.html',
  styleUrls: ['./users-overview.component.scss'],
})
export class UsersOverviewComponent {
  @Input() usersCount = 0;
  @Input() propertiesCount = 0;
  @Input() devicesCount = 0;
  @Input() alarmsCount = 0;
}
