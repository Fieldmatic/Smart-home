import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsrPendingTableComponent } from './csr-pending-table.component';

describe('CsrPendingTableComponent', () => {
  let component: CsrPendingTableComponent;
  let fixture: ComponentFixture<CsrPendingTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsrPendingTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CsrPendingTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
