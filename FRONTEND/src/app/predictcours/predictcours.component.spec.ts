import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictcoursComponent } from './predictcours.component';

describe('PredictcoursComponent', () => {
  let component: PredictcoursComponent;
  let fixture: ComponentFixture<PredictcoursComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PredictcoursComponent]
    });
    fixture = TestBed.createComponent(PredictcoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
