import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { QuestionService } from '../services/question/question.service';
import { Evaluation } from '../Models/evaluation.model';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { Question } from '../Models/question.model'; // Assurez-vous d'avoir ce modèle

@Component({
  selector: 'app-dashbordevaluation',
  templateUrl: './dashbordevaluation.component.html',
  styleUrls: ['./dashbordevaluation.component.css']
})
export class DashbordevaluationComponent {
  evaluations: Evaluation[] = [];
  questions: Question[] = []; // Nouvelle propriété pour les questions
  loading = false;
  activeTab: 'evaluations' | 'questions' = 'evaluations'; // Pour gérer les onglets

  constructor(
    private evaluationService: EvaluationService,
    private questionService: QuestionService, // Injection du service des questions
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadEvaluations();
    this.loadQuestions(); // Charger aussi les questions au démarrage
  }

  loadEvaluations(): void {
    this.loading = true;
    this.evaluationService.getAllEvaluations().subscribe({
      next: (data) => {
        this.evaluations = data;
        this.loading = false;
      },
      error: (err) => {
        this.snackBar.open('Erreur de chargement des évaluations', 'Fermer', { duration: 3000 });
        this.loading = false;
      }
    });
  }

  loadQuestions(): void {
    this.loading = true;
    this.questionService.getAllQuestions().subscribe({
      next: (data) => {
        this.questions = data;
        this.loading = false;
      },
      error: (err) => {
        this.snackBar.open('Erreur de chargement des questions', 'Fermer', { duration: 3000 });
        this.loading = false;
      }
    });
  }

  navigateToEditEvaluation(idEvaluation: string): void {
    this.router.navigate(['/admin/evaluations/edit', idEvaluation]);
  }

  navigateToEditQuestion(idQuestion: string): void {
    this.router.navigate(['/admin/questions/edit', idQuestion]);
  }

  navigateToAddEvaluation(): void {
    this.router.navigate(['/add-evaluation']);
  }

  navigateToAddQuestion(): void {
    this.router.navigate(['/add-question']);
  }

  deleteEvaluation(id: string): void {
    if (confirm('Confirmer la suppression de l\'évaluation ?')) {
      this.evaluationService.deleteEvaluation(id).subscribe({
        next: () => {
          this.snackBar.open('Évaluation supprimée avec succès', 'Fermer', { duration: 2000 });
          this.loadEvaluations();
        },
        error: () => {
          this.snackBar.open('Erreur de suppression de l\'évaluation', 'Fermer', { duration: 3000 });
        }
      });
    }
  }

  deleteQuestion(id: string): void {
    if (confirm('Confirmer la suppression de la question ?')) {
      this.questionService.deleteQuestion(id).subscribe({
        next: () => {
          this.snackBar.open('Question supprimée avec succès', 'Fermer', { duration: 2000 });
          this.loadQuestions();
        },
        error: () => {
          this.snackBar.open('Erreur de suppression de la question', 'Fermer', { duration: 3000 });
        }
      });
    }
  }

  switchTab(tab: 'evaluations' | 'questions'): void {
    this.activeTab = tab;
  }

}
