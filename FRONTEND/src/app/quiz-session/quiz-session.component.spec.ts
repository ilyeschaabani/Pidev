import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizSessionComponent } from './quiz-session.component';

describe('QuizSessionComponent', () => {
  let component: QuizSessionComponent;
  let fixture: ComponentFixture<QuizSessionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuizSessionComponent]
    });
    fixture = TestBed.createComponent(QuizSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
