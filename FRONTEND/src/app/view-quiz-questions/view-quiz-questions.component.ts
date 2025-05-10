import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from '../Models/question.model';
import { ToastrService } from 'ngx-toastr';
import { QuestionService } from '../services/question/question.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { QuizResultService } from '../services/questionresult/quiz-result.service';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent {
  questions: Question[] = [];
  currentQuestionIndex = 0;
  userAnswers: { [key: string]: string } = {};
  quizCompleted = false;
  score = 0;
  showResults = false;
  selectedOption: string | null = null;
  isLoading = true;
  idEvaluation: any | undefined;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private questionService: QuestionService,
    private quizResultService: QuizResultService
  ) {}

  ngOnInit(): void {
    this.idEvaluation = this.route.snapshot.paramMap.get('id') || '';
  
    // Vérifie si un score est déjà enregistré
    const savedScore = localStorage.getItem(`score_${this.idEvaluation}`);
    if (savedScore !== null) {
      this.score = parseInt(savedScore, 10);
      this.quizCompleted = true;
      this.showResults = true;
      this.toastr.info(`Vous avez déjà complété ce quiz. Score : ${this.score}/${this.questions.length}`);
      return;
    }
  
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state?.['questions']) {
      this.questions = state['questions'];
      this.isLoading = false;
    } else {
      this.loadQuestions();
    }
  }
  

  loadQuestions(): void {
    this.questionService.getQuestionsByEvaluation(this.idEvaluation)
      .pipe(
        catchError(error => {
          this.toastr.error('Failed to load questions');
          this.router.navigate(['/evaluation-list']);
          return of([]);
        })
      )
      .subscribe(questions => {
        this.questions = questions;
        this.isLoading = false;
        if (questions.length === 0) {
          this.toastr.warning('No questions found for this quiz');
        }
      });
  }


  get currentQuestion(): Question {
    return this.questions[this.currentQuestionIndex];
  }

  get options(): string[] {
    return [
      this.currentQuestion.option1,
      this.currentQuestion.option2,
      this.currentQuestion.option3,
      this.currentQuestion.option4
    ].filter(opt => opt && opt.trim() !== '');
  }

  selectAnswer(option: string): void {
    this.selectedOption = option;
    this.userAnswers[this.currentQuestion.quesId!] = option;
  }

  nextQuestion(): void {
    if (!this.selectedOption) {
      this.toastr.warning('Please select an answer');
      return;
    }

    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;
      this.selectedOption = this.userAnswers[this.currentQuestion.quesId!] || null;
    } else {
      this.finishQuiz();
    }
  }

  prevQuestion(): void {
    if (this.currentQuestionIndex > 0) {
      this.currentQuestionIndex--;
      this.selectedOption = this.userAnswers[this.currentQuestion.quesId!] || null;
    }
  }

  finishQuiz(): void {
    this.calculateScore();
    this.quizCompleted = true;
    this.showResults = true;
    this.toastr.success(`Quiz completed! Score: ${this.score}/${this.questions.length}`);
  }

  calculateScore(): void {
    this.score = this.questions.reduce((acc, question) => {
      return acc + (this.userAnswers[question.quesId!] === question.answer ? 1 : 0);
    }, 0);
  }

  get progress(): number {
    return ((this.currentQuestionIndex + 1) / this.questions.length) * 100;
  }

  navigateToEvaluationList(): void {
    this.router.navigate(['/evaluation-list']);
  }
 /* submitQuiz() {
    const score = this.calculateScore();
    
    // Navigation vers le dashboard de gamification avec le score
    this.router.navigate(['/gamification'], {
      state: {
        idEvaluation: this.idEvaluation,
        score: score
      }
    });
  }*/

    submitQuiz() {
      const score = this.score;
      const idEvaluation = this.idEvaluation;
    
      // ✅ Enregistrer score dans localStorage
      localStorage.setItem(`score_${idEvaluation}`, score.toString());
    
      this.quizResultService.saveQuizResult(idEvaluation, score)
        .subscribe({
          next: () => {
            this.toastr.success('Score enregistré avec succès!');
            this.router.navigate(['/Gamification'], {
              state: {
                idEvaluation: idEvaluation,
                score: score,
                questionsCount: this.questions.length,
                correctAnswers: score
              }
            });
          },
          error: (err) => {
            this.toastr.error('Erreur lors de l\'enregistrement du score');
            console.error(err);
          }
        });
    }
    
}
