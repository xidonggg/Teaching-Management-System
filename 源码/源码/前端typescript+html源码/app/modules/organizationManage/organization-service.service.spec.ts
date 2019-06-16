import { TestBed } from '@angular/core/testing';

import { OrganizationServiceService } from './organization-service.service';

describe('OrganizationServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrganizationServiceService = TestBed.get(OrganizationServiceService);
    expect(service).toBeTruthy();
  });
});
