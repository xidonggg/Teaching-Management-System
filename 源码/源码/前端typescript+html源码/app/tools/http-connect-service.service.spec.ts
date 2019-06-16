import { TestBed } from '@angular/core/testing';

import { HttpConnectServiceService } from './http-connect-service.service';

describe('HttpConnectServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpConnectServiceService = TestBed.get(HttpConnectServiceService);
    expect(service).toBeTruthy();
  });
});
