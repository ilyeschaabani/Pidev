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
explication: string | null = null;

  predictionResult: number | null = null;
  loading = false;
  validationError: string | null = null;

  projectTypes = ['Renovation', 'Innovation',  'Other'];
  taskStatuses = ['Completed', 'On track', 'Behind'];
  priorities = ['Medium', 'High'];

  constructor(
    private predictionService: PredictionService,
    private dialog: MatDialog
  ) {}

  validerChamps(): boolean {
    const d = this.inputData;

    // Nom du projet
    if (!d['Project Name'] || d['Project Name'].trim().length < 3) {
      this.validationError = "🏷️ Le nom du projet doit être significatif.";
      return false;
    }

    // Type de projet logique
    if (!this.projectTypes.includes(d['Project Type'])) {
      this.validationError = `🏗️ Type de projet invalide. Choisissez parmi : ${this.projectTypes.join(', ')}.`;
      return false;
    }

    // Statut de la tâche
    if (!this.taskStatuses.includes(d['Task Status'])) {
      this.validationError = `🧩 Statut de tâche invalide. Options : ${this.taskStatuses.join(', ')}.`;
      return false;
    }

    // Priorité logique
    if (!this.priorities.includes(d['Priority'])) {
      this.validationError = `⚠️ Priorité invalide. Valeurs possibles : ${this.priorities.join(', ')}.`;
      return false;
    }

    // Budget
    if (d['Budget'] <= 0) {
      this.validationError = "💰 Le budget doit être supérieur à 0.";
      return false;
    }

    // Heures passées
    if (d['Hours Spent'] < 0) {
      this.validationError = "⏱️ Les heures passées ne peuvent pas être négatives.";
      return false;
    }

    // Progression
    if (d['Progress'] < 0 || d['Progress'] > 100) {
      this.validationError = "📈 La progression doit être entre 0 et 100%.";
      return false;
    }

    // Dates
    const start = new Date(d['Start Date']);
    const end = new Date(d['End Date']);
    if (start > end) {
      this.validationError = "📅 La date de début doit être avant la date de fin.";
      return false;
    }

    // Toutes les validations sont bonnes
    this.validationError = null;
    return true;
  }

genererExplication(data: any, cout: number): string {
  const progression = data['Progress'];
  const budget = data['Budget'];
  const heures = data['Hours Spent'];
  const priorite = data['Priority'];
  const statut = data['Task Status'];

  let message = `🔍 Analyse du coût estimé : `;

  if (progression < 30) {
    message += `le projet est encore à un stade initial (${progression}%), ce qui peut expliquer un coût encore modéré. `;
  } else if (progression < 70) {
    message += `le projet est en cours (${progression}%), ce qui représente une progression normale. `;
  } else {
    message += `le projet est presque terminé (${progression}%), donc le coût estimé devrait être proche du coût final. `;
  }

  if (priorite === 'Élevée') {
    message += `La priorité élevée peut impliquer des ressources supplémentaires, augmentant potentiellement les coûts. `;
  }

  if (heures > 100) {
    message += `Un grand nombre d'heures passées (${heures}) indique une intensité de travail significative. `;
  }

  if (budget < cout) {
    message += `⚠️ Le coût estimé dépasse le budget prévu (${budget} DT), une réévaluation est peut-être nécessaire.`;
  } else {
    message += `✅ Le coût estimé reste dans le budget prévu (${budget} DT).`;
  }

  return message;
}

  envoyerPrediction() {
  if (!this.validerChamps()) return;

  this.loading = true;
 this.predictionService.predict(this.inputData).subscribe(
  (response) => {
    this.loading = false;
    this.predictionResult = response.predicted_cost;
    this.explication = this.genererExplication(this.inputData, response.predicted_cost);

    this.dialog.open(PredictionResultDialogComponent, {
      data: { predicted_cost: response.predicted_cost }
    });
  },
    (error) => {
      this.loading = false;
      console.error('❌ Erreur:', error);
      alert('Erreur lors de la prédiction.');
    }
  );
}

  }
