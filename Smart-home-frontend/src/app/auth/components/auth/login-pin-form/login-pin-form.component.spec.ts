import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginPinFormComponent } from './login-pin-form.component';

describe('LoginPinFormComponent', () => {
  let component: LoginPinFormComponent;
  let fixture: ComponentFixture<LoginPinFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginPinFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginPinFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
