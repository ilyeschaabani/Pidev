import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Projet } from '../../models/projet.model';

@Injectable({
  providedIn: 'root'
})
export class ProjetService {
  private baseUrl = 'http://localhost:8082/api/projets';

  constructor(private http: HttpClient) {}

  getAllProjets(): Observable<Projet[]> {
    return this.http.get<Projet[]>(`${this.baseUrl}/all`).pipe(
      catchError(this.handleError('Erreur lors du chargement des projets'))
    );
  }

  addProjet(projet: Partial<Projet>): Observable<Projet> {
    return this.http.post<Projet>(`${this.baseUrl}/add`, projet).pipe(
      catchError(this.handleError("Erreur lors de l'ajout du projet"))
    );
  }

  getProjetById(id: string): Observable<Projet> {
    return this.http.get<Projet>(`${this.baseUrl}/get/${id}`).pipe(
      catchError(this.handleError('Projet introuvable'))
    );
  }

  updateProjet(id: string, projet: Projet): Observable<Projet> {
    return this.http.put<Projet>(`${this.baseUrl}/update/${id}`, projet).pipe(
      catchError(this.handleError('Erreur lors de la mise à jour'))
    );
  }

  deleteProjet(idProjet: string): Observable<any> {
    console.log("Suppression du projet avec ID:", idProjet);  // Ajouter un log pour vérifier l'ID
    return this.http.delete(`${this.baseUrl}/delete/${idProjet}`).pipe(
      catchError(this.handleError('Erreur lors de la suppression du projet'))
    );
  }
  
  validateOrRejectProjet(id: string, isValid: boolean, rejectionMotif?: string): Observable<Projet> {
    return this.http.put<Projet>(`${this.baseUrl}/validate-or-reject/${id}?isValid=${isValid}&rejectionMotif=${rejectionMotif || ''}`, {});
  }

  // Centralisation de la gestion des erreurs
  private handleError(message: string) {
    return (error: any) => {
      console.error(message, error);
      return throwError(() => new Error(message + ' : ' + (error.message || error.statusText)));
    };
  }
}
