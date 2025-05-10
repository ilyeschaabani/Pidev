import { Component } from '@angular/core';
import { PredictionService } from '../services/project/prediction.service';
import { MatDialog } from '@angular/material/dialog';
import { PredictionResultDialogComponent } from '../prediction-result-dialog/prediction-result-dialog.component';

@Component({
  selector: 'app-prediction-project',
  templateUrl: './prediction-project.component.html',
  styleUrls: ['./prediction-project.component.css']
})
export class PredictionProjectComponent {
  inputData: any = {
    "Project Name": "",
    "Project Type": "",
    "Location": "",
    "Project Status": "",
    "Priority": "",
    "Task ID": "",
    "Task Name": "",
    "Task Status": "",
    "Assigned To": "",
    "Hours Spent": null,
    "Budget": null,
    "Progress": null,
    "Start Date": "",
    "End Date": ""
  };
  predictionResult: number | null = null;

  constructor(
    private predictionService: PredictionService,
    private dialog: MatDialog // ⬅️ Inject le MatDialog
  ) {}

  envoyerPrediction() {
    this.predictionService.predict(this.inputData).subscribe(
      (response) => {
        console.log('✅ Réponse reçue:', response);
        this.predictionResult = response.predicted_cost;

        this.dialog.open(PredictionResultDialogComponent, {
          data: { predicted_cost: response.predicted_cost }
        });
      },
      (error) => {
        console.error('❌ Erreur:', error);
        alert('Erreur de prédiction');
      }
    );
  }
}
