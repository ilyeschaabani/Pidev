import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashbordevaluationComponent } from './dashbordevaluation.component';

describe('DashbordevaluationComponent', () => {
  let component: DashbordevaluationComponent;
  let fixture: ComponentFixture<DashbordevaluationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashbordevaluationComponent]
    });
    fixture = TestBed.createComponent(DashbordevaluationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
