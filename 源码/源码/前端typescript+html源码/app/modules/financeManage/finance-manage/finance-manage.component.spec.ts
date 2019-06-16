import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceManageComponent } from './finance-manage.component';

describe('FinanceManageComponent', () => {
  let component: FinanceManageComponent;
  let fixture: ComponentFixture<FinanceManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinanceManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
