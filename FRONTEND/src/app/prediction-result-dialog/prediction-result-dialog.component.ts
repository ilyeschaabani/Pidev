import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-prediction-result-dialog',
  templateUrl: './prediction-result-dialog.component.html',
  styleUrls: ['./prediction-result-dialog.component.css']
})
export class PredictionResultDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

}
