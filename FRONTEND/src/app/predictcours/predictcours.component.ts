import { Component } from '@angular/core';
import { CertifServiceService } from '../services/certif/certif-service.service';

@Component({
  selector: 'app-predictcours',
  templateUrl: './predictcours.component.html',
  styleUrls: ['./predictcours.component.css']
})
export class PredictcoursComponent {
  predictionData = {
    TimeSpentOnCourse: 0,
    NumberOfVideosWatched: 0,
    NumberOfQuizzesTaken: 0,
    QuizScores: 0,
    CompletionRate: 0,
    DeviceType: 0
  };
  
  predictionText: string = '';
  probability: number = 0;
  loading = false;
  errorDetails: string = '';

  constructor(private certifService: CertifServiceService) {}

  getPrediction() {
    this.loading = true;
    this.errorDetails = '';
    
    this.certifService.getPrediction(this.predictionData).subscribe({
      next: (response) => {
        this.loading = false;
        if (response.status === 'success') {
          this.predictionText = `Prédiction: ${response.prediction === 1 ? 'Complété' : 'Non complété'}`;
          this.probability = response.probability * 100;
        } else {
          this.predictionText = 'Erreur dans la prédiction';
          this.errorDetails = response.error || 'Détails non disponibles';
        }
      },
      error: (error) => {
        this.loading = false;
        console.error('Erreur complète:', error);
        this.predictionText = 'Erreur de communication avec le serveur';
        this.errorDetails = error.message || 'Vérifiez la console pour plus de détails';
      }
    });
  }
}