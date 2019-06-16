import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationManageCPComponent } from './organization-manage-cp.component';

describe('OrganizationManageCPComponent', () => {
  let component: OrganizationManageCPComponent;
  let fixture: ComponentFixture<OrganizationManageCPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizationManageCPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationManageCPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
