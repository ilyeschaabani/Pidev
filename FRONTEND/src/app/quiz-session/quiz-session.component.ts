import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { lastValueFrom } from 'rxjs';
import { QuestionService } from '../services/question/question.service';
import { ToastrService } from 'ngx-toastr';
import { Question } from '../Models/question.model';
import { Evaluation } from '../Models/evaluation.model';

@Component({
  selector: 'app-quiz-session',
  templateUrl: './quiz-session.component.html',
  styleUrls: ['./quiz-session.component.css']
})
export class QuizSessionComponent {
  getOptionLetter(_t24: number) {
    throw new Error('Method not implemented.');
    }
      evaluation!: Evaluation;
      questions: Question[] = [];
      currentQuestionIndex = 0;
      userAnswers: { [key: string]: string } = {};
      score = 0;
      quizCompleted = false;
      showResults = false;
      isLoading = true;
      timer: any;
      timeLeft: number = 0;
    
      constructor(
        private route: ActivatedRoute,
        private router: Router,
        private questionService: QuestionService,
        private evaluationService: EvaluationService,
        private toastr: ToastrService
      ) {}
    
      ngOnInit(): void {
        const idEvaluation = this.route.snapshot.params['id'];
        this.loadQuizData(idEvaluation);
      }
    
      loadQuizData(idEvaluation: string): void {
        this.isLoading = true;
    
        // Chargement parallèle de l'évaluation et des questions
        this.evaluationService.getEvaluationById(idEvaluation).subscribe({
          next: (evaluation) => {
            this.evaluation = evaluation;
            this.timeLeft = evaluation.duree * 60; // Convertir en secondes
            this.startTimer();
            this.loadQuestions(idEvaluation);
          },
          error: (err) => this.handleError('Failed to load evaluation', err)
        });
      }
    
      loadQuestions(idEvaluation: string): void {
        this.questionService.getQuestionsByEvaluation(idEvaluation).subscribe({
          next: (questions) => {
            this.questions = this.shuffleQuestions(questions);
            this.isLoading = false;
            
            if (this.questions.length === 0) {
              this.toastr.warning('No questions available for this quiz');
              this.router.navigate(['/evaluations']);
            }
          },
          error: (err) => this.handleError('Failed to load questions', err)
        });
      }
    
      startTimer(): void {
        this.timer = setInterval(() => {
          if (this.timeLeft > 0) {
            this.timeLeft--;
          } else {
            this.completeQuiz();
            clearInterval(this.timer);
          }
        }, 1000);
      }
    
      get formattedTime(): string {
        const minutes = Math.floor(this.timeLeft / 60);
        const seconds = this.timeLeft % 60;
        return `${minutes}:${seconds < 10 ? '0' + seconds : seconds}`;
      }
    
      shuffleQuestions(questions: Question[]): Question[] {
        return [...questions].sort(() => Math.random() - 0.5);
      }
    
      get currentQuestion(): Question {
        return this.questions[this.currentQuestionIndex];
      }
    
      get options(): string[] {
        const options = [
          this.currentQuestion.option1,
          this.currentQuestion.option2,
          this.currentQuestion.option3,
          this.currentQuestion.option4
        ].filter(opt => opt?.trim());
        
        return this.shuffleOptions(options);
      }
    
      shuffleOptions(options: string[]): string[] {
        return [...options].sort(() => Math.random() - 0.5);
      }
    
      selectAnswer(option: string): void {
        this.userAnswers[this.currentQuestion.quesId!] = option;
      }
    
      nextQuestion(): void {
        if (!this.userAnswers[this.currentQuestion.quesId!]) {
          this.toastr.warning('Please select an answer');
          return;
        }
    
        if (this.currentQuestionIndex < this.questions.length - 1) {
          this.currentQuestionIndex++;
        } else {
          this.completeQuiz();
        }
      }
    
      prevQuestion(): void {
        if (this.currentQuestionIndex > 0) {
          this.currentQuestionIndex--;
        }
      }
    
      completeQuiz(): void {
        clearInterval(this.timer);
        this.calculateScore();
        this.quizCompleted = true;
        this.showResults = true;
        
        this.evaluationService.submitEvaluation(this.evaluation.idEvaluation!, {
          answers: this.userAnswers,
          score: this.score
        }).subscribe({
          next: () => this.toastr.success('Quiz submitted successfully!'),
          error: (err) => this.toastr.error('Failed to submit quiz results')
        });
      }
    
      calculateScore(): void {
        this.score = this.questions.reduce((total, question) => {
          return total + (this.userAnswers[question.quesId!] === question.answer ? 1 : 0);
        }, 0);
      }
    
      get progress(): number {
        return ((this.currentQuestionIndex + 1) / this.questions.length) * 100;
      }
    
      get scorePercentage(): number {
        return Math.round((this.score / this.questions.length) * 100);
      }
    
      restartQuiz(): void {
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.userAnswers = {};
        this.quizCompleted = false;
        this.showResults = false;
        this.timeLeft = this.evaluation.duree * 60;
        this.startTimer();
      }
    
      exitQuiz(): void {
        clearInterval(this.timer);
        this.router.navigate(['/evaluations']);
      }
    
      private handleError(message: string, error: any): void {
        console.error(message, error);
        this.toastr.error(message);
        this.isLoading = false;
        this.router.navigate(['/evaluations']);
      }

}
