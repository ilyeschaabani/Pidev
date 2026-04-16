import { TestBed } from '@angular/core/testing';

import { EvaluationService } from './EvaluationService';

describe('QuizService', () => {
  let service: EvaluationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EvaluationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
