import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyDevicesCardComponent } from './property-devices-card.component';

describe('PropertyDevicesCardComponent', () => {
  let component: PropertyDevicesCardComponent;
  let fixture: ComponentFixture<PropertyDevicesCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropertyDevicesCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropertyDevicesCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
