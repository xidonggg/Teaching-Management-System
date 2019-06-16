import { TestBed } from '@angular/core/testing';

import { TimeTableServiceService } from './time-table-service.service';

describe('TimeTableServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TimeTableServiceService = TestBed.get(TimeTableServiceService);
    expect(service).toBeTruthy();
  });
});
