import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailmanageComponent } from './detailmanage.component';

describe('DetailmanageComponent', () => {
  let component: DetailmanageComponent;
  let fixture: ComponentFixture<DetailmanageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailmanageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailmanageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
