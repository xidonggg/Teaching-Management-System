import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollmentPlanComponent } from './enrollment-plan.component';

describe('EnrollmentPlanComponent', () => {
  let component: EnrollmentPlanComponent;
  let fixture: ComponentFixture<EnrollmentPlanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnrollmentPlanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrollmentPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
