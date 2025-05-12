import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
<<<<<<< HEAD
  private apiUrl = 'http://127.0.0.1:5001'; // Update if your Flask server is hosted elsewhere
=======
  private apiUrl = 'http://localhost:5006'; // <-- Corrigé
>>>>>>> bf9b88a5d3cff4af8701a6bd70861613575cebbc

  constructor(private http: HttpClient) { }

  getExpectedFeatures(): Observable<any> {
    return this.http.get(`${this.apiUrl}/features`);
  }

  predictCourseDifficulty(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(`${this.apiUrl}/predict`, data, { headers });
  }
  chatWithBot(message: string, context: any): Observable<any> {
  return this.http.post<any>(`${this.apiUrl}/chat`, {
    message,
    context
  });
}
}
