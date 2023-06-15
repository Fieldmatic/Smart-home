import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyProfileAlarmsComponent } from './property-profile-alarms.component';

describe('PropertyProfileAlarmsComponent', () => {
  let component: PropertyProfileAlarmsComponent;
  let fixture: ComponentFixture<PropertyProfileAlarmsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropertyProfileAlarmsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropertyProfileAlarmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
