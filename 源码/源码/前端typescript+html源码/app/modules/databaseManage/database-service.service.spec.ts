import { TestBed } from '@angular/core/testing';

import { DatabaseServiceService } from './database-service.service';

describe('DatabaseServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DatabaseServiceService = TestBed.get(DatabaseServiceService);
    expect(service).toBeTruthy();
  });
});
