import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GamificationService {
  getLeaderboard() {
    throw new Error('Method not implemented.');
  }

  private apiUrl = 'http://localhost:8093/gamification'; // Changez l'URL selon votre configuration

  constructor(private http: HttpClient) { }

  // Méthode pour traiter les résultats du quiz
  processQuizResults(idUser: string, quizRecord: any): Observable<any> {
    const url = `${this.apiUrl}/process-quiz-results/${idUser}`;
    return this.http.post<any>(url, quizRecord, {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    });
  }

  // Méthode pour obtenir les informations de gamification d'un utilisateur
  getUserGamification(idUser: string): Observable<any> {
    const url = `${this.apiUrl}/get-user-gamification/${idUser}`;
    return this.http.get<any>(url);
  }

  // Méthode pour obtenir les badges d'un utilisateur
  getUserBadges(idUser: string): Observable<any> {
    const url = `${this.apiUrl}/get-user-badges/${idUser}`;
    return this.http.get<any>(url);
  }

  getUserStats(idUser: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/${idUser}/stats`);
  }

}
