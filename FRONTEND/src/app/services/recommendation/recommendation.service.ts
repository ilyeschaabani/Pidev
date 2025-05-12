import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  private baseUrl = 'http://localhost:5003';

  constructor(private http: HttpClient) {}

  getRecommendations(level: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/recommend`, { instructional_level: level }, { headers });
  }

  predictLevel(data: {
    headline: string,
    objectives: string,
    curriculum: string
  }): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.baseUrl}/predict_level`, data, { headers });
  }
}
