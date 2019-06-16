import { TestBed } from '@angular/core/testing';

import { FieldServiceService } from './field-service.service';

describe('FieldServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FieldServiceService = TestBed.get(FieldServiceService);
    expect(service).toBeTruthy();
  });
});
