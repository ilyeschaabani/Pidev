import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PredictionRimaService {

  private apiUrl = 'http://localhost:5009/predict';

  constructor(private http: HttpClient) { }
  getPrediction(predictionData: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Validation des données avant envoi
    const requiredFields = [
      'TimeSpentOnCourse', 'NumberOfVideosWatched',
      'NumberOfQuizzesTaken', 'QuizScores',
      'CompletionRate', 'DeviceType'
    ];

    for (const field of requiredFields) {
      if (predictionData[field] === undefined || predictionData[field] === null) {
        return throwError(() => new Error(`Le champ ${field} est requis`));
      }
    }

    return this.http.post<any>(this.apiUrl, predictionData, { headers })
      .pipe(
        catchError((error) => {
          console.error('Erreur HTTP:', error);
          return throwError(() => new Error(
            error.error?.message || 
            error.message || 
            'Erreur de communication avec le serveur'
          ));
        })
      );
  }

}
