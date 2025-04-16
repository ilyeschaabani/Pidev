import { Component } from '@angular/core';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Evaluation } from '../Models/evaluation.model';

@Component({
  selector: 'app-addquiz',
  templateUrl: './addquiz.component.html',
  styleUrls: ['./addquiz.component.css']
})
export class AddquizComponent {
  evaluation: Evaluation = {
    idEvaluation: '',
    titre: '',
    description: '',
    maxMarks: '0',
    noOfQuestions: '0',
    active: false,
    note: 0,
    dateCreation: '',
    duree: 0,
  };

  isLoading = false; // Ajout d'un indicateur de chargement

  constructor(
    private evaluationService: EvaluationService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.validateForm() || this.isLoading) {
      return;
    }

    this.isLoading = true;
    
    this.evaluationService.createEvaluation(this.evaluation, 0, undefined, '').subscribe({
      next: (response) => {
        this.isLoading = false;
        this.router.navigate(['/affecter', response.idEvaluation], )
          .then(() => {
            this.snackBar.open('Quiz créé avec succès! Redirection...', 'Fermer', { 
              duration: 2000,
              panelClass: ['success-snackbar']
            });
          });
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open('Erreur lors de la création: ' + err.message, 'Fermer', { 
          duration: 5000,
          panelClass: ['error-snackbar']
        });
        console.error('Erreur création:', err);
      }
    });
  }

  private validateForm(): boolean {
    if (!this.evaluation.titre?.trim()) {
      this.snackBar.open('Le titre est requis', 'Fermer', { duration: 3000 });
      return false;
    }
    return true;
  }

}
