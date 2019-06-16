import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TimeTableManageComponent } from './time-table-manage.component';

describe('TimeTableManageComponent', () => {
  let component: TimeTableManageComponent;
  let fixture: ComponentFixture<TimeTableManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TimeTableManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TimeTableManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
