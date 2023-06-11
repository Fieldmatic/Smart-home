import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropertyProfileDevicesComponent } from './property-profile-devices.component';

describe('ProperyProfileDevicesComponent', () => {
  let component: PropertyProfileDevicesComponent;
  let fixture: ComponentFixture<PropertyProfileDevicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PropertyProfileDevicesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropertyProfileDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
