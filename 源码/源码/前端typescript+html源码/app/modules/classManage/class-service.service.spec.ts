import { TestBed } from '@angular/core/testing';

import { ClassServiceService } from './class-service.service';

describe('ClassServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClassServiceService = TestBed.get(ClassServiceService);
    expect(service).toBeTruthy();
  });
});
