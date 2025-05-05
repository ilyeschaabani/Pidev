import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private apiUrl = 'http://127.0.0.1:5000'; // Update if your Flask server is hosted elsewhere

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
}