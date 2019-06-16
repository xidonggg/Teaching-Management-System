import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchievementDetailComponent } from './achievement-detail.component';

describe('AchievementDetailComponent', () => {
  let component: AchievementDetailComponent;
  let fixture: ComponentFixture<AchievementDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchievementDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchievementDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
