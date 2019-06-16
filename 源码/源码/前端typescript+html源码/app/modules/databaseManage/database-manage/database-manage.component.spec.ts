import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabaseManageComponent } from './database-manage.component';

describe('DatabaseManageComponent', () => {
  let component: DatabaseManageComponent;
  let fixture: ComponentFixture<DatabaseManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatabaseManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatabaseManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
