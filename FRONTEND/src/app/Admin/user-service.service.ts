import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private baseUrl = 'http://localhost:8082/api/users';

  constructor(private http: HttpClient) {}

  getEncadrants(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/encadrants`);
  }

  assignEncadrant(projetId: string, encadrantId: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/assign-encadrant/${projetId}/${encadrantId}`, {});
  }
}
