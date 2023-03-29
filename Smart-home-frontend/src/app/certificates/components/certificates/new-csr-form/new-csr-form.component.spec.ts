import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCsrFormComponent } from './new-csr-form.component';

describe('NewCsrFormComponent', () => {
  let component: NewCsrFormComponent;
  let fixture: ComponentFixture<NewCsrFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewCsrFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCsrFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
