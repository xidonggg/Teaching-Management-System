import { TestBed } from '@angular/core/testing';

import { EnrollmentServiceService } from './enrollment-service.service';

describe('EnrollmentServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EnrollmentServiceService = TestBed.get(EnrollmentServiceService);
    expect(service).toBeTruthy();
  });
});
