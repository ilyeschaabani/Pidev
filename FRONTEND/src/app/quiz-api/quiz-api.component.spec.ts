import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizAPIComponent } from './quiz-api.component';

describe('QuizAPIComponent', () => {
  let component: QuizAPIComponent;
  let fixture: ComponentFixture<QuizAPIComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizAPIComponent]
    });
    fixture = TestBed.createComponent(QuizAPIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
