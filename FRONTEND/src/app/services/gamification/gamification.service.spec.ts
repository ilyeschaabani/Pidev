import { TestBed } from '@angular/core/testing';

import { GamificationService } from '../services copy/gamification.service';

describe('GamificationService', () => {
  let service: GamificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GamificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
