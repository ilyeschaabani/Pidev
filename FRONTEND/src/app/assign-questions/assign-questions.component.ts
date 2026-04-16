import { Component } from '@angular/core';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { QuestionService } from '../services/question/question.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Evaluation } from '../Models/evaluation.model';
import { Question } from '../Models/question.model'; // Assurez-vous d'avoir ce modèle
import { lastValueFrom } from 'rxjs';


@Component({
  selector: 'app-assign-questions',
  templateUrl: './assign-questions.component.html',
  styleUrls: ['./assign-questions.component.css']
})
export class AssignQuestionsComponent {
  evaluations: Evaluation[] = [];
  allQuestions: Question[] = [];
  selectedEvaluationId: string | null = null;
  selectedQuestionIds = new Set<string>();
  
  isLoading = {
    evaluations: false,
    questions: false,
    assignment: false
  };

  constructor(
    private evaluationService: EvaluationService,
    private questionService: QuestionService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadAllData();
  }

  loadAllData(): void {
    this.loadEvaluations();
    this.loadAllQuestions();
  }

  loadEvaluations(): void {
    this.isLoading.evaluations = true;
    this.evaluationService.getAllEvaluations().subscribe({
      next: (evals) => {
        this.evaluations = evals;
        this.isLoading.evaluations = false;
      },
      error: (err) => {
        console.error('Error loading evaluations:', err);
        this.toastr.error('Failed to load evaluations');
        this.isLoading.evaluations = false;
      }
    });
  }

  loadAllQuestions(): void {
    this.isLoading.questions = true;
    this.questionService.getAllQuestions().subscribe({
      next: (questions) => {
        this.allQuestions = questions;
        this.isLoading.questions = false;
      },
      error: (err) => {
        console.error('Error loading questions:', err);
        this.toastr.error('Failed to load questions');
        this.isLoading.questions = false;
      }
    });
  }

  toggleQuestionSelection(questionId: string): void {
    if (this.selectedQuestionIds.has(questionId)) {
      this.selectedQuestionIds.delete(questionId);
    } else {
      this.selectedQuestionIds.add(questionId);
    }
  }

  async assignQuestionsToEvaluation() {
    if (!this.selectedEvaluationId || this.selectedQuestionIds.size === 0) {
      this.toastr.warning('Please select an evaluation and at least one question');
      return;
    }
  
    this.isLoading.assignment = true;
  
    try {
      // Convertir le Set en tableau pour l'envoi à l'API
      const questionIdsArray = Array.from(this.selectedQuestionIds);
      
      // Appeler le service pour assigner les questions
      const result = await lastValueFrom(
        this.evaluationService.addQuestionsToEvaluation(
          this.selectedEvaluationId,
          questionIdsArray
        )
      );
      
      this.toastr.success('Questions assigned successfully!');
      this.router.navigate(['/evaluation-list']);
    } catch (error) {
      console.error('Assignment error:', error);
      this.toastr.error(
        error instanceof Error ? error.message : 'Failed to assign questions',
        'Error',
        { timeOut: 5000 }
      );
    } finally {
      this.isLoading.assignment = false;
    }
  }

  getShortContent(question: Question): string {
    if (question.contenu) return question.contenu;
    if (question.texte) return question.texte;
    if (question.contenu) return question.contenu;
    return 'No question text';
  }
}
