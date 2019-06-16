import { TestBed } from '@angular/core/testing';

import { SchoolyearServiceService } from './schoolyear-service.service';

describe('SchoolyearServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SchoolyearServiceService = TestBed.get(SchoolyearServiceService);
    expect(service).toBeTruthy();
  });
});
