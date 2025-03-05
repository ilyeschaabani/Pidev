import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ressource } from '../models/ressource.model';

@Injectable({ providedIn: 'root' })
export class RessourceService {
  private apiUrl = 'http://localhost:8082/api/ressources';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Ressource[]> {
    return this.http.get<Ressource[]>(this.apiUrl);
  }

  getById(id: string): Observable<Ressource> {
    return this.http.get<Ressource>(`${this.apiUrl}/${id}`);
  }

  create(res: Ressource): Observable<Ressource> {
    return this.http.post<Ressource>(this.apiUrl, res);
  }

  update(id: string, res: Ressource): Observable<Ressource> {
    return this.http.put<Ressource>(`${this.apiUrl}/${id}`, res);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  
  
}
