import { Component, OnInit } from '@angular/core';
import { RessourceService } from 'src/app/ServicesRessource/ressource-service.service';
import { Ressource, TypeRessource, CategoryRessource } from 'src/app/models/ressource.model';
@Component({
  selector: 'app-ressource-list',
  templateUrl: './ressource-list.component.html',
  styleUrls: ['./ressource-list.component.css']
})
export class RessourceListComponent implements OnInit {
  ressources: Ressource[] = [];
  newRes: Partial<Ressource> = {};
  types = Object.values(TypeRessource);
  categories = Object.values(CategoryRessource);
  errorMessage = '';

  constructor(private service: RessourceService) {}

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
    if(confirm('Confirmer suppression ?')) {
      this.service.delete(id).subscribe({
        next: () => {
          this.ressources = this.ressources.filter(r => r.idRessource !== id);
          this.errorMessage = '';
        },
        error: (err) => this.errorMessage = 'Erreur suppression: ' + err.message
      });
    }
  }
}