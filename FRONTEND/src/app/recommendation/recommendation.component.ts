import { Component } from '@angular/core';
import { RecommendationService } from '../services/recommendation/recommendation.service';
@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent {
   headline: string = '';
  objectives: string = '';
  curriculum: string = '';
  level: string = '';
  recommendations: any[] = [];
  errorMessage = '';

  constructor(private recoService: RecommendationService) {}


  onPredictAndRecommend() {
    if (!this.headline && !this.objectives && !this.curriculum) {
      this.errorMessage = 'Veuillez remplir au moins un champ.';
      return;
    }

    this.errorMessage = '';
    this.recoService.predictLevel({
      headline: this.headline,
      objectives: this.objectives,
      curriculum: this.curriculum
    }).subscribe({
      next: (res) => {
        this.level = res.predicted_level;
        this.getRecommendations();
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la prédiction du niveau.';
        console.error(err);
      }
    });
  }

  getRecommendations() {
    this.recoService.getRecommendations(this.level).subscribe({
      next: (res) => {
        this.recommendations = res.recommendations || [];
        this.errorMessage = '';
      },
      error: (err) => {
        this.recommendations = [];
        this.errorMessage = 'Erreur lors de la récupération des recommandations.';
        console.error(err);
      }
    });
  }
}

