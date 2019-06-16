import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationManageComponent } from './organization-manage.component';

describe('OrganizationManageComponent', () => {
  let component: OrganizationManageComponent;
  let fixture: ComponentFixture<OrganizationManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizationManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
