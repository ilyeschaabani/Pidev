import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Evaluation } from '../../Models/evaluation.model';
import { Question } from '../../Models/question.model';

@Injectable({
  providedIn: 'root'
})
export class EvaluationService {
  private readonly apiUrl = 'http://localhost:8093/evaluations';

  constructor(private http: HttpClient) {}

  // Créer une évaluation
  createEvaluation(evaluation: Evaluation, p0: number, undefined: undefined, p1: string): Observable<Evaluation> {
    return this.http.post<Evaluation>(`${this.apiUrl}/add`, evaluation)
  }

  // Récupérer toutes les évaluations
  getAllEvaluations(): Observable<Evaluation[]> {
    return this.http.get<Evaluation[]>(`${this.apiUrl}/listeval`)
  
  }

  // Récupérer une évaluation par ID
  getEvaluationById(id: string): Observable<Evaluation> {
    return this.http.get<Evaluation>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Mettre à jour une évaluation
  updateEvaluation(id: string, evaluation: Evaluation): Observable<Evaluation> {
    return this.http.put<Evaluation>(`${this.apiUrl}/${id}`, evaluation)
      .pipe(catchError(this.handleError));
  }

  // Supprimer une évaluation
  deleteEvaluation(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Soumettre une évaluation
  submitEvaluation(evaluationId: string, answers: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/${evaluationId}/submit`, answers)
      .pipe(catchError(this.handleError));
  }

  addQuestionsToEvaluation(idEvaluation: string, quesIds: string[]): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/${idEvaluation}/questions`, 
      quesIds
    );
  }
  // Récupérer toutes les questions
  getAllQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.apiUrl}/questions`)
      .pipe(catchError(this.handleError));
  }

  // Récupérer une évaluation avec ses questions
  getEvaluationWithQuestions(evaluationId: string): Observable<Evaluation> {
    return this.http.get<Evaluation>(`${this.apiUrl}/${evaluationId}/with-questions`)
      .pipe(catchError(this.handleError));
  }

  // Gestion des erreurs
  private handleError(error: any): Observable<never> {
    console.error('An error occurred:', error);
    return throwError(() => new Error(
      error.error?.message || error.message || 'Une erreur est survenue'
    ));
  }
}