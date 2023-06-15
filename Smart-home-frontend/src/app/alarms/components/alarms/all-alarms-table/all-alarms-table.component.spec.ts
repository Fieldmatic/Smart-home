import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAlarmsTableComponent } from './all-alarms-table.component';

describe('AllAlarmsTableComponent', () => {
  let component: AllAlarmsTableComponent;
  let fixture: ComponentFixture<AllAlarmsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAlarmsTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllAlarmsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
