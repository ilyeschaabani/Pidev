import { TestBed } from '@angular/core/testing';

import { PredictionRimaService } from './prediction-rima.service';

describe('PredictionRimaService', () => {
  let service: PredictionRimaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PredictionRimaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
