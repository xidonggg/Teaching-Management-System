import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchievementManageComponent } from './achievement-manage.component';

describe('AchievementManageComponent', () => {
  let component: AchievementManageComponent;
  let fixture: ComponentFixture<AchievementManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchievementManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchievementManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
