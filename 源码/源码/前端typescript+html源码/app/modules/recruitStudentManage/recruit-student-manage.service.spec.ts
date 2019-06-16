import { TestBed } from '@angular/core/testing';

import { RecruitStudentManageService } from './recruit-student-manage.service';

describe('RecruitStudentManageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecruitStudentManageService = TestBed.get(RecruitStudentManageService);
    expect(service).toBeTruthy();
  });
});
