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
  switchTab(arg0: string) {
    throw new Error('Method not implemented.');
    }
    navigateToAddQuestion() {
    throw new Error('Method not implemented.');
    }
    navigateToEditQuestion(arg0: any) {
    throw new Error('Method not implemented.');
    }
    deleteQuestion(arg0: any) {
    throw new Error('Method not implemented.');
    }
      evaluations: Evaluation[] = [];
      loading = false;
    activeTab: any;
    questions: any;
    
      constructor(
        private evaluationService: EvaluationService,
        private router: Router,
        private snackBar: MatSnackBar
      ) {}
    
      ngOnInit(): void {
        this.loadEvaluations();
      }
    
      loadEvaluations(): void {
        this.loading = true;
        this.evaluationService.getAllEvaluations().subscribe({
          next: (data) => {
            this.evaluations = data;
            this.loading = false;
          },
          error: (err) => {
            this.snackBar.open('Erreur de chargement', 'Fermer', { duration: 3000 });
            this.loading = false;
          }
        });
      }
     /* navigateToEditEvaluation(idEvaluation: string): void {
        console.log('Attempting to navigate to edit evaluation with ID:', idEvaluation);
        if (!idEvaluation) {
          console.error('Invalid evaluation ID');
          this.snackBar.open('ID d\'évaluation invalide', 'Fermer', { duration: 3000 });
          return;
        }
        this.router.navigate(['/admin/evaluations/edit', idEvaluation]);
      }
    */
    // Dans AdminDashboardComponentComponent
    
    
    
    // Version finale qui devrait fonctionner
    navigateToEditEvaluation(idEvaluation: string): void {
      // Solution 1 (recommandée si ça marche)
      this.router.navigate(['/admin/evaluations/edit', idEvaluation]);
      
      // Si Solution 1 échoue, utiliser Solution 2 :
      // this.router.navigateByUrl(/admin/evaluations/edit/${idEvaluation});
      
      // Si tout échoue, gardez la solution de repli :
      // window.location.href = /admin/evaluations/edit/${idEvaluation};
    }
    navigateToAddEvaluation(): void {
      this.router.navigate(['/quiz/add']);
    }
      deleteEvaluation(id: string): void {
        if (confirm('Confirmer la suppression ?')) {
          this.evaluationService.deleteEvaluation(id).subscribe({
            next: () => {
              this.snackBar.open('Supprimé avec succès', 'Fermer', { duration: 2000 });
              this.loadEvaluations();
            },
            error: () => {
              this.snackBar.open('Erreur de suppression', 'Fermer', { duration: 3000 });
            }
          });
        }
      }}