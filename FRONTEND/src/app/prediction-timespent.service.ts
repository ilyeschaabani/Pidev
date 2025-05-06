import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PredictionTimespentService {
  private apiUrl = 'http://localhost:5008/predict'; // Update if your Flask server is hosted elsewhere
  constructor(private http: HttpClient) { }

  getPredictions(features: any): Observable<any> {
    return this.http.post(this.apiUrl, features);
  }
  
}
