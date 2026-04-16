import { TestBed } from '@angular/core/testing';

import { CertifServiceService } from '../certif/certif-service.service';

describe('CertifServiceService', () => {
  let service: CertifServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CertifServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
