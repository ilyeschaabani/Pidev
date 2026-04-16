import { TestBed } from '@angular/core/testing';
import { ProjetService } from './project.service';
describe('ProjetService', () => { // Correction du nom
  let service: ProjetService; // Correction du nom

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjetService); // Correction du nom
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
