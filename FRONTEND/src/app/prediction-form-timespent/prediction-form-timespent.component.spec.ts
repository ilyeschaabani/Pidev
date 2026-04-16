import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionFormTimespentComponent } from './prediction-form-timespent.component';

describe('PredictionFormTimespentComponent', () => {
  let component: PredictionFormTimespentComponent;
  let fixture: ComponentFixture<PredictionFormTimespentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredictionFormTimespentComponent]
    });
    fixture = TestBed.createComponent(PredictionFormTimespentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
