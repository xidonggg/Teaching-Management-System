import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClueDetailComponent } from './clue-detail.component';

describe('ClueDetailComponent', () => {
  let component: ClueDetailComponent;
  let fixture: ComponentFixture<ClueDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClueDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClueDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
