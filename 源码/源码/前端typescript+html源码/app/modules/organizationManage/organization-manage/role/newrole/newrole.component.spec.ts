import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewroleComponent } from './newrole.component';

describe('NewroleComponent', () => {
  let component: NewroleComponent;
  let fixture: ComponentFixture<NewroleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewroleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewroleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
