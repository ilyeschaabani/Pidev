import { Component } from '@angular/core';
import { EvaluationService } from '../services/Evaluation/EvaluationService';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Evaluation } from '../Models/evaluation.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addquiz',
  templateUrl: './addquiz.component.html',
  styleUrls: ['./addquiz.component.css']
})
export class AddquizComponent {
  isLoading = false;
  quizForm: FormGroup;

  constructor(
    private evaluationService: EvaluationService,
    private snackBar: MatSnackBar,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.quizForm = this.fb.group({
      titre: ['', [Validators.required, Validators.minLength(3)]],
      description: [''],
      maxMarks: ['20', [Validators.required, Validators.min(1)]],
      noOfQuestions: ['5', [Validators.required, Validators.min(1)]],
      duree: ['30', [Validators.required, Validators.min(1)]],
      active: [true]
    });
  }

  onSubmit(): void {
    if (this.quizForm.invalid || this.isLoading) {
      this.markFormGroupTouched(this.quizForm);
      return;
    }

    this.isLoading = true;
    
    const evaluationToCreate = {
      ...this.quizForm.value,
      idEvaluation: undefined,
      dateCreation: new Date().toISOString(),
      note: 0,
      questions: []
    };

    this.evaluationService.createEvaluation(evaluationToCreate, 0, undefined, '').subscribe({
      next: (createdEvaluation) => {
        this.isLoading = false;
        this.snackBar.open('Quiz créé avec succès!', 'Fermer', { 
          duration: 2000,
          panelClass: ['success-snackbar']
        });
        this.quizForm.reset({
          maxMarks: '20',
          noOfQuestions: '5',
          duree: '30',
          active: true
        });
      },
      error: (err) => {
        this.isLoading = false;
        this.snackBar.open(`Erreur: ${err.error?.message || 'Erreur serveur'}`, 'Fermer', {
          duration: 5000
        });
        console.error('Erreur création:', err);
      }
    });
  }

  navigateToQuizList(): void {
    this.router.navigate(['/affecter']);
  }
  navigateToQuestions() {
    this.router.navigate(['/question/add']);
    }
  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }

  get f() { return this.quizForm.controls; }


}