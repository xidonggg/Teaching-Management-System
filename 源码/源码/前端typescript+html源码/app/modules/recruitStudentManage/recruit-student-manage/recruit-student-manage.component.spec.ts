import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecruitStudentManageComponent } from './recruit-student-manage.component';

describe('RecruitStudentManageComponent', () => {
  let component: RecruitStudentManageComponent;
  let fixture: ComponentFixture<RecruitStudentManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecruitStudentManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecruitStudentManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
