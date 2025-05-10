import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionProjectComponent } from './prediction-project.component';

describe('PredictionProjectComponent', () => {
  let component: PredictionProjectComponent;
  let fixture: ComponentFixture<PredictionProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredictionProjectComponent]
    });
    fixture = TestBed.createComponent(PredictionProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
