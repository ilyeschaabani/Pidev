import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertifServiceService {
  assignCertif(certifId: string, userId: string) {
    throw new Error('Method not implemented.');
  }



  private apiUrl = 'http://localhost:8093/api/certif'; // URL de votre backend

  constructor(private http: HttpClient) { }

  // Télécharger un certificat (retourne un Blob)
  downloadCertif(idCertif: string): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/download/${idCertif}`, {
      responseType: 'blob' // Indique que la réponse est un fichier binaire
    });
  }

  // Autres méthodes si nécessaire (ex: récupérer la liste des certificats)
  getCertifs(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
