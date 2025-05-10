import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MentorService {
  private baseUrl  = 'http://localhost:9010/api/projects';

  constructor(private http: HttpClient) { }

  // Récupérer tous les projets
  getProjects(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  // Ajouter une évaluation pour un projet
  addEvaluation(projectId: string, evaluationData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${projectId}/evaluate`, evaluationData);
  }

  // Ajouter un commentaire pour un projet
  addComment(projectId: string, commentData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${projectId}/comment`, commentData);
  }
}