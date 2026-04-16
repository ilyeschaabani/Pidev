import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Evaluation } from '../Models/evaluation.model';
import { EvaluationService } from '../services/Evaluation/EvaluationService';


@Component({
  selector: 'app-modifiequestion',
  templateUrl: './modifiequestion.component.html',
  styleUrls: ['./modifiequestion.component.css']
})
export class ModifiequestionComponent {
  evaluationForm: FormGroup;
  idEvaluation!: string;
  loading = false;
location: any;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private evaluationService: EvaluationService,
    private snackBar: MatSnackBar
  ) {
    this.evaluationForm = this.fb.group({
      titre: ['', [Validators.required, Validators.minLength(3)]],
      description: [''],
      duree: ['', [Validators.required, Validators.min(1)]],
      maxMarks: ['', [Validators.required, Validators.min(1)]],
      noOfQuestions: ['', [Validators.required, Validators.min(1)]],
      active: [true]
    });
  }

  ngOnInit(): void {
    this.idEvaluation = this.route.snapshot.paramMap.get('idEvaluation')!;
    this.loadEvaluationData();
  }
  loadEvaluationData(): void {
    this.loading = true;
    this.evaluationService.addQuestionsToEvaluation(this.idEvaluation, []).subscribe({
      next: (evaluation) => {
        this.evaluationForm.patchValue({
          titre: evaluation.titre,
          description: evaluation.description,
          duree: evaluation.duree,
          maxMarks: evaluation.maxMarks,
          noOfQuestions: evaluation.noOfQuestions,
          active: evaluation.active
        });
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        this.snackBar.open('Erreur de chargement', 'Fermer', { duration: 3000 });
        this.router.navigate(['/admin']);
      }
    });
  }

  onSubmit(): void {
    if (this.evaluationForm.invalid) return;

    this.loading = true;
    const formData = this.evaluationForm.value;
    const evaluationData: Evaluation = {
      ...formData,
      idEvaluation: this.idEvaluation
    };

    this.evaluationService.updateEvaluation(this.idEvaluation, evaluationData).subscribe({
      next: () => {
        this.snackBar.open('Évaluation mise à jour', 'Fermer', { duration: 2000 });
        this.router.navigate(['/admin']);
      },
      error: () => {
        this.loading = false;
        this.snackBar.open('Erreur de mise à jour', 'Fermer', { duration: 3000 });
      }
    });
  }
  navigateToAdmin() {
    this.location.back();
    }

}
