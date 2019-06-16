import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyMessageManageComponent } from './my-message-manage.component';

describe('MyMessageManageComponent', () => {
  let component: MyMessageManageComponent;
  let fixture: ComponentFixture<MyMessageManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyMessageManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyMessageManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
