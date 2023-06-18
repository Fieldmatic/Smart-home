import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-events-overview',
  templateUrl: './events-overview.component.html',
  styleUrls: ['./events-overview.component.scss'],
})
export class EventsOverviewComponent {
  @Input() logsCount = 0;
  @Input() messagesCount = 0;
  @Input() alarmsCount = 0;
}
