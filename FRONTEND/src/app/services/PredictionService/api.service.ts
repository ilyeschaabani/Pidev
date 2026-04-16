import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://localhost:5006';  // Ou '/api' si vous utilisez le proxy

  constructor(private http: HttpClient) { }

  chatWithBot(message: string, context: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/chat`, {
      message,
      context
    });
  }

  predictCourseDifficulty(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/predict_difficulty`, data);
  }

  getExpectedFeatures(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/model_info`);
  }
}