import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDeviceRuleDialogComponent } from './add-device-rule-dialog.component';

describe('AddDeviceRuleDialogComponent', () => {
  let component: AddDeviceRuleDialogComponent;
  let fixture: ComponentFixture<AddDeviceRuleDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDeviceRuleDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDeviceRuleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
