import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileStorageService {

  private apiUrl = 'http://localhost:9009/api/files'; // Remplace avec ton URL

  constructor(private http: HttpClient) {}

  // Méthode pour envoyer un fichier à l'API
  uploadFile(file: File, fileType: 'IMAGE' | 'VIDEO'): Observable<{ fileName: string }> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('fileType', fileType);

    const headers = new HttpHeaders();

    // Appel API pour uploader le fichier
    return this.http.post<{ fileName: string }>(`${this.apiUrl}/upload`, formData, { headers });
  }

  // Optionnel: Méthode pour télécharger un fichier
  downloadFile(fileType: 'IMAGE' | 'VIDEO', fileName: string): String {
    return `${this.apiUrl}/download/${fileType}/${fileName}`;
  }
}
