import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ressource, TypeRessource, CategoryRessource } from 'src/app/models/ressource.model';
import { RessourceService } from 'src/app/ServicesRessource/ressource-service.service';

@Component({
  selector: 'app-add-ressource',
  templateUrl: './add-ressource.component.html',
  styleUrls: ['./add-ressource.component.css']
})
export class AddRessourceComponent {
  ressource: Ressource = {
    idRessource: '',
    titre: '',
    description: '',
    type: TypeRessource.DOCUMENT,
    date: new Date(),
    category: CategoryRessource.OTHER
  };
  types = Object.values(TypeRessource);
  categories = Object.values(CategoryRessource);

  successMessage: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private ressourceService: RessourceService) {
    this.resetForm();
  }

  onSubmit(): void {
    this.ressourceService.create(this.ressource).subscribe({
      next: (response: any) => {
        this.successMessage = 'Ressource ajoutée avec succès';
        this.resetForm();
      },
      error: (error: any) => {
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
