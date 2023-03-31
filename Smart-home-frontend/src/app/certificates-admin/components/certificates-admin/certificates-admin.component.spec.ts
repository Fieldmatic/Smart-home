import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CertificatesAdminComponent } from './certificates-admin.component';

describe('CertificatesAdminComponent', () => {
  let component: CertificatesAdminComponent;
  let fixture: ComponentFixture<CertificatesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CertificatesAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CertificatesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
