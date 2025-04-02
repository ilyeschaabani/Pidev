
import { ProjectFile } from '../models/project-file.model';
import { HttpClient, HttpHeaders } from '@angular/common/http'; // Add HttpHeaders
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FileService {

  private apiUrl = 'http://localhost:8082/api/files';  // L'URL de votre API Spring Boot

  constructor(private http: HttpClient) {}


  // Télécharger un fichier pour un projet
  uploadFile(projectId: string, formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/upload/${projectId}`, formData);
  }

  // Ajoutez ces méthodes au FileService


  getProjectFiles(projectId: string): Observable<ProjectFile[]> {
    return this.http.get<ProjectFile[]>(
      `${this.apiUrl}/project/${projectId}`
    );
  }
downloadFile(fileId: string): Observable<Blob> {
  return this.http.get(`${this.apiUrl}/download/${fileId}`, {
    responseType: 'blob'
  });
}
}
