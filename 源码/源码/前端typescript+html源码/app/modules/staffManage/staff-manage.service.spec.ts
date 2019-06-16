import { TestBed } from '@angular/core/testing';

import { StaffManageService } from './staff-manage.service';

describe('StaffManageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StaffManageService = TestBed.get(StaffManageService);
    expect(service).toBeTruthy();
  });
});
