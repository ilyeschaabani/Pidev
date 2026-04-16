import { TestBed } from '@angular/core/testing';

import { PredictionTimespentService } from './prediction-timespent.service';

describe('PredictionTimespentService', () => {
  let service: PredictionTimespentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PredictionTimespentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
