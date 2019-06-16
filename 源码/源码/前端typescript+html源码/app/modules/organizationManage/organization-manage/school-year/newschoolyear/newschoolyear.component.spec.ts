import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewschoolyearComponent } from './newschoolyear.component';

describe('NewschoolyearComponent', () => {
  let component: NewschoolyearComponent;
  let fixture: ComponentFixture<NewschoolyearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewschoolyearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewschoolyearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
