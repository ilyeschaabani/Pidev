import { Component } from '@angular/core';
import {PredictionTimespentService } from '../prediction-timespent.service';

@Component({
  selector: 'app-prediction-form-timespent',
  templateUrl: './prediction-form-timespent.component.html',
  styleUrls: ['./prediction-form-timespent.component.css']
})
export class PredictionFormTimespentComponent {

  features = {
    'Age': 30,
    'Income Level': 50000,
    'Device Usage': 5,
    'Education Level': 3,
    'Likes and Reactions': 1500,
    'Followed Accounts': 200,
    'Total Time Spent Online': 4,
    'Top Interests': 'Technology,Sports'
  };
  
  predictions: any;
  error: string | null = null;
  constructor(private predictionService: PredictionTimespentService) { }

  onSubmit() {
    this.error = null;
    this.predictions = null;
    
    this.predictionService.getPredictions(this.features).subscribe(
      (response) => {
        this.predictions = response;
      },
      (error) => {
        this.error = error.message || 'An error occurred';
      }
    );
  }
}
