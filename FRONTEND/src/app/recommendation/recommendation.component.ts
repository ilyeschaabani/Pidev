import { Component } from '@angular/core';
import { RecommendationService } from '../services/recommendation/recommendation.service';
@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css']
})
export class RecommendationComponent {
  level = '';
  recommendations: any[] = [];
  errorMessage = '';

  constructor(private recoService: RecommendationService) {}

  onSubmit() {
    if (!this.level.trim()) {
      this.errorMessage = 'Veuillez entrer un niveau.';
      this.recommendations = [];
      return;
    }

    this.recoService.getRecommendations(this.level.trim()).subscribe({
      next: (res) => {
        this.recommendations = res.recommendations || [];
        this.errorMessage = '';
      },
      error: (err) => {
        console.error(err);
        this.recommendations = [];
        this.errorMessage = 'Erreur lors de la récupération des recommandations.';
      }
    });
  }
}
