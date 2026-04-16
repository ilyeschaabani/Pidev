import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { GeneratedTopic , Project , TopicGenerationRequest } from '../../Models/projects.model';
@Injectable({ providedIn: 'root' })
export class PfeService {
  private baseUrl = 'http://localhost:9010/api/projects';

  constructor(private http: HttpClient) {}

  getStudentProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/student`);
  }

  uploadDocument(projectId: string, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('document', file);
    return this.http.post(`${this.baseUrl}/${projectId}/document`, formData);
  }

  generateTopics(request: TopicGenerationRequest): Observable<string> {
    return this.http.post(`${this.baseUrl}/generate-topics`, request, { responseType: 'text' });
  }
  
}