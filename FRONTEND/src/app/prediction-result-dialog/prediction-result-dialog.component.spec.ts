import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionResultDialogComponent } from './prediction-result-dialog.component';

describe('PredictionResultDialogComponent', () => {
  let component: PredictionResultDialogComponent;
  let fixture: ComponentFixture<PredictionResultDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredictionResultDialogComponent]
    });
    fixture = TestBed.createComponent(PredictionResultDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
