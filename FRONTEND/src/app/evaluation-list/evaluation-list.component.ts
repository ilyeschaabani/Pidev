import { Component } from '@angular/core';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { QuestionService } from '../services/question/question.service';
import { Router, NavigationExtras } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Evaluation } from '../Models/evaluation.model';
import { Question } from '../Models/question.model';

@Component({
  selector: 'app-evaluation-list',
  templateUrl: './evaluation-list.component.html',
  styleUrls: ['./evaluation-list.component.css']
})
export class EvaluationListComponent {
  evaluations: Evaluation[] = [];
  questionsMap: { [key: string]: Question[] } = {};
  loadingStates: { [key: string]: boolean } = {};
  expandedEvaluation: string | null = null;

  constructor(
    private evaluationService: EvaluationService,
    private questionService: QuestionService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.loadEvaluations();
    
    // Vérifie si on vient de l'assignation de questions
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {refresh?: boolean, idEvaluation?: string};
    
    if (state?.refresh && state.idEvaluation) {
      this.loadQuestionsForEvaluation(state.idEvaluation);
      this.toastr.success('Questions assigned successfully!');
    }
  }

  loadEvaluations(): void {
    this.evaluationService.getAllEvaluations().subscribe({
      next: (evals) => {
        this.evaluations = evals;
        evals.forEach(evaluation => {
          this.loadQuestionsForEvaluation(evaluation.idEvaluation);
        });
      },
      error: (err) => {
        console.error('Error loading evaluations:', err);
        this.toastr.error('Failed to load evaluations');
      }
    });
  }
  loadQuestionsForEvaluation(evalId: string) {
    this.questionService.getQuestionsByEvaluation(evalId).subscribe({
      next: (questions) => {
        if (questions.length > 0) {
          console.log('Questions trouvées:', questions);
          this.questionsMap[evalId] = questions;
        } else {
          console.log('Aucune question pour cette évaluation');
        }
      },
      error: (err) => {
        console.error('Erreur:', err);
      }
    });
  }

  getEvaluationStatus(evaluationId: string): string {
    const questions = this.questionsMap[evaluationId];
    if (!questions || questions.length === 0) return 'No questions assigned';
    return `Ready (${questions.length} questions)`;
  }
  startEvaluation(idEvaluation: string): void {
    // Vérifie d'abord dans le cache local
    if (this.questionsMap[idEvaluation]?.length > 0) {
      this.router.navigate(['/view-questions', idEvaluation], {
        state: { 
          questions: [...this.questionsMap[idEvaluation]],
          idEvaluation 
        }
      });
    } else {
      // Fallback: charge depuis l'API si pas dans le cache
      this.questionService.getQuestionsByEvaluation(idEvaluation).subscribe({
        next: (questions) => {
          if (questions.length > 0) {
            this.router.navigate(['/view-questions', idEvaluation], {
              state: { questions, idEvaluation }
            });
          } else {
            this.toastr.warning('No questions available for this quiz');
          }
        },
        error: (err) => {
          this.toastr.error('Failed to load quiz questions');
        }
      });
    }
  }
  toggleEvaluationDetails(idEvaluation: string): void {
    this.expandedEvaluation = this.expandedEvaluation === idEvaluation ? null : idEvaluation;
  }

 // Méthode corrigée pour l'assignation des questions
 assignQuestions(evaluation: Evaluation): void {
  // Navigation vers la page d'assignation (/quiz/:id)
  this.router.navigate(['/quiz', evaluation.idEvaluation], {
    state: {
      evaluationTitle: evaluation.titre,
      existingQuestionIds: this.getExistingQuestionIds(evaluation.idEvaluation)
    }
  });
}
// Méthode pour récupérer les IDs des questions existantes
private getExistingQuestionIds(idEvaluation: string): string[] {
  return this.questionsMap[idEvaluation]?.map(q => q.quesId).filter((id): id is string => id !== undefined) || [];
}

  // Méthode de navigation
  navigateToQuizapi(): void {
    this.router.navigate(['/quiz']); // Redirection vers la route '/dashboard'
  }

}
