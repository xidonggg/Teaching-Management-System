import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewfieldComponent } from './newfield.component';

describe('NewfieldComponent', () => {
  let component: NewfieldComponent;
  let fixture: ComponentFixture<NewfieldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewfieldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewfieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
