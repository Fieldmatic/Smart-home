import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProperyProfileDevicesComponent } from './propery-profile-devices.component';

describe('ProperyProfileDevicesComponent', () => {
  let component: ProperyProfileDevicesComponent;
  let fixture: ComponentFixture<ProperyProfileDevicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProperyProfileDevicesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProperyProfileDevicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
