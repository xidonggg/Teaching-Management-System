import { TestBed } from '@angular/core/testing';

import { FinanceServiceService } from './finance-service.service';

describe('FinanceServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FinanceServiceService = TestBed.get(FinanceServiceService);
    expect(service).toBeTruthy();
  });
});
