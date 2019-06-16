import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceDeatilComponent } from './finance-deatil.component';

describe('FinanceDeatilComponent', () => {
  let component: FinanceDeatilComponent;
  let fixture: ComponentFixture<FinanceDeatilComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinanceDeatilComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinanceDeatilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
