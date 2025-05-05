import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/PredictionService/api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-prediction',
  templateUrl: './prediction.component.html',
  styleUrls: ['./prediction.component.css']
})
export class PredictionComponent implements OnInit {
  predictionForm: FormGroup;
  expectedFeatures: string[] = [];
  predictionResult: any;
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private apiService: ApiService,
    private fb: FormBuilder
  ) {
    this.predictionForm = this.fb.group({
      'Course Name': ['', Validators.required],
      'University': [''],
      'Course Rating': [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      'Skills': ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.apiService.getExpectedFeatures().subscribe({
      next: (response) => {
        this.expectedFeatures = response.expected_features;
      },
      error: (err) => {
        console.error('Error fetching expected features:', err);
      }
    });
  }

  onSubmit(): void {
    if (this.predictionForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';
      this.predictionResult = null;
      
      const formData = this.predictionForm.value;
      
      this.apiService.predictCourseDifficulty(formData).subscribe({
        next: (result) => {
          this.predictionResult = result;
          this.isLoading = false;
        },
        error: (err) => {
          this.errorMessage = err.error?.error || 'An error occurred during prediction';
          this.isLoading = false;
        }
      });
    }
  }
}