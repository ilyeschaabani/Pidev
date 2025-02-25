import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projet } from '../models/projet.model';  // Assurez-vous d'avoir un modèle de projet

@Injectable({
  providedIn: 'root'
})
export class ProjetService {

  private baseUrl = 'http://localhost:8082/api/projets';  // L'URL de votre backend Spring Boot

  constructor(private http: HttpClient) { }

  // Méthode pour obtenir tous les projets
  getAllProjets(): Observable<Projet[]> {
    return this.http.get<Projet[]>(`${this.baseUrl}/all`);
  }

  // Méthode pour ajouter un projet
  addProjet(projet: Projet): Observable<Projet> {
    return this.http.post<Projet>(`${this.baseUrl}/add`, projet);
  }

  // Méthode pour obtenir un projet par son ID
  getProjetById(id: string): Observable<Projet> {
    return this.http.get<Projet>(`${this.baseUrl}/get/${id}`);
  }

  // Méthode pour mettre à jour un projet
  updateProjet(id: string, projet: Projet): Observable<Projet> {
    return this.http.put<Projet>(`${this.baseUrl}/update/${id}`, projet);
  }

  // Méthode pour supprimer un projet
  deleteProjet(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
