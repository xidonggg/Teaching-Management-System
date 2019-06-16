import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizationMessageComponent } from './organization-message.component';

describe('OrganizationMessageComponent', () => {
  let component: OrganizationMessageComponent;
  let fixture: ComponentFixture<OrganizationMessageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizationMessageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizationMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
