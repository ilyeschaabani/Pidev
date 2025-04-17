import { TestBed } from '@angular/core/testing';

import { PfeProjectService } from './pfe-project.service';

describe('PfeProjectService', () => {
  let service: PfeProjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PfeProjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
