import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyAlarmsCardComponent } from './property-alarms-card.component';

describe('PropertyAlarmsCardComponent', () => {
  let component: PropertyAlarmsCardComponent;
  let fixture: ComponentFixture<PropertyAlarmsCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropertyAlarmsCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropertyAlarmsCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
