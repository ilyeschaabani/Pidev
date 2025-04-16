import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define or import the QuizResult interface
export interface QuizResult {
  id: string;
  idEvaluation: string;
  score: number;
  idUser: string;
}

@Injectable({
  providedIn: 'root'
})
export class QuizResultService {
  private apiUrl = 'http://localhost:8093/quiz-results'; // Port 8093

  constructor(private http: HttpClient) { }

  saveQuizResult(idEvaluation: string, score: number): Observable<QuizResult> {
    return this.http.post<QuizResult>(this.apiUrl, null, {
      params: {
        idEvaluation: idEvaluation,
        score: score.toString()
      }
    });
  }

  getUserResults(idUser: string): Observable<QuizResult[]> {
    return this.http.get<QuizResult[]>(`${this.apiUrl}/user/${idUser}`);
  }

  getUserStats(idUser: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/${idUser}/stats`);
  }
}