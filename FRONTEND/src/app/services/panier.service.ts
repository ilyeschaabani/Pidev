import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Panier } from '../models/panier.model';

@Injectable({
  providedIn: 'root'
})
export class PanierService {
  private baseUrl = 'http://localhost:8083/api/panier'; // adjust if needed

  constructor(private http: HttpClient) {}

  getPanier(userId: number): Observable<Panier> {
    return this.http.get<Panier>(`${this.baseUrl}/${userId}`);
  }

  addFormation(userId: number, formationId: string): Observable<Panier> {
    return this.http.post<Panier>(`${this.baseUrl}/${userId}/add/${formationId}`, {});
  }

  removeFormation(userId: number, formationId: string): Observable<Panier> {
    return this.http.delete<Panier>(`${this.baseUrl}/${userId}/remove/${formationId}`);
  }

  clearPanier(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/clear/${userId}`);
  }
  
  
}
