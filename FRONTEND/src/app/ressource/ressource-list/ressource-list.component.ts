import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Assurez-vous d'importer HttpClient
import { RessourceService } from 'src/app/ServicesRessource/ressource-service.service';
import { Ressource, TypeRessource, CategoryRessource } from 'src/app/models/ressource.model';

@Component({
  selector: 'app-ressource-list',
  templateUrl: './ressource-list.component.html',
  styleUrls: ['./ressource-list.component.css']
})
export class RessourceListComponent implements OnInit {
  ressources: Ressource[] = [];
  ressource: Ressource = {
    idRessource: '',
    titre: '',
    description: '',
    type: TypeRessource.DOCUMENT, // Assurez-vous que ce type est défini dans votre modèle
    date: new Date(),
    category: CategoryRessource.OTHER // Assurez-vous que cette catégorie est définie
  };
  types = Object.values(TypeRessource);
  categories = Object.values(CategoryRessource);
  
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private service: RessourceService, private http: HttpClient) {}

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.service.getAll().subscribe({
      next: (data) => this.ressources = data,
      error: (err) => this.errorMessage = 'Erreur de chargement: ' + err.message
    });
  }

  deleteRessource(id: string): void {
    if (confirm('Confirmer suppression ?')) {
      this.service.delete(id).subscribe({
        next: () => {
          this.ressources = this.ressources.filter(r => r.idRessource !== id);
        },
        error: (err) => this.errorMessage = 'Erreur suppression: ' + err.message
      });
    }
  }

  onSubmit(): void {
    this.service.create(this.ressource).subscribe({
      next: () => {
        this.successMessage = 'Ressource ajoutée avec succès';
        this.resetForm();
        this.loadData(); // Recharge la liste des ressources après ajout
      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Erreur lors de l\'ajout de la ressource';
      }
    });
  }

  resetForm(): void {
    this.ressource = { 
      idRessource: '', 
      titre: '', 
      description: '', 
      type: TypeRessource.DOCUMENT, 
      date: new Date(), 
      category: CategoryRessource.OTHER 
    };
    this.successMessage = '';
    this.errorMessage = '';
  }
}
