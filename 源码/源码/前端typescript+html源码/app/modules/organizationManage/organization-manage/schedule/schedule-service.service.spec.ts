import { TestBed } from '@angular/core/testing';

import { ScheduleServiceService } from './schedule-service.service';

describe('ScheduleServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScheduleServiceService = TestBed.get(ScheduleServiceService);
    expect(service).toBeTruthy();
  });
});
