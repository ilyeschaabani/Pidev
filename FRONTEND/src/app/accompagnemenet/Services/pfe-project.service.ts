import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PFEProject, DocumentFile } from '../models/pfe-project.model';

@Injectable({
  providedIn: 'root'
})
export class PfeProjectService {

  private apiUrl = 'http://localhost:8082/api/projects';

  constructor(private http: HttpClient) {}


  getProjects(): Observable<PFEProject[]> {
    return this.http.get<PFEProject[]>(this.apiUrl);
  }

  createProject(project: PFEProject): Observable<PFEProject> {
    return this.http.post<PFEProject>(this.apiUrl, project);
  }

 
  addDocument(id: string, document: DocumentFile, currentUsername: string): Observable<PFEProject> {
    const params = new HttpParams().set('currentUsername', currentUsername);
    return this.http.post<PFEProject>(`${this.apiUrl}/${id}/document`, document, { params });
  }
  updateProject(id: string, project: PFEProject): Observable<PFEProject> {
    return this.http.put<PFEProject>(`${this.apiUrl}/${id}`, project);
  }
  
  deleteProject(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  

 

  getProjectById(projetId: string): Observable<PFEProject> {
    return this.http.get<PFEProject>(`${this.apiUrl}/${projetId}`);
  }
  generateTopics(keywords: { keywords: string }): Observable<string[]> {
    return this.http.post<string[]>(`${this.apiUrl}/generate-topics`, keywords);
  }
  

}