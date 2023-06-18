import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.scss'],
})
export class TimerComponent implements OnInit, OnDestroy, OnChanges {
  @Input() time!: number;
  timeLeft!: number;
  private interval: NodeJS.Timer | undefined;
  @Output() timerDone: EventEmitter<boolean> = new EventEmitter<boolean>();

  ngOnInit() {
    this.startTimer();
  }

  ngOnDestroy() {
    this.stopTimer();
  }

  ngOnChanges() {
    this.timeLeft = this.time;
    this.stopTimer();
    this.startTimer();
  }

  private startTimer() {
    this.interval = setInterval(() => {
      if (this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.stopTimer();
        this.timerDone.emit();
      }
    }, 1000);
  }

  private stopTimer() {
    clearInterval(this.interval);
  }
}
