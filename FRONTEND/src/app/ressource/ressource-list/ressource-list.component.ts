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
  deleteRessource(id: any): void {
    if (confirm('Confirmer suppression ?')) {
      console.log(id);
      this.service.delete(id).subscribe({
        next: () => {
          this.ressources = this.ressources.filter(r => r.idRessource !== id);
        },
        error: (err) => {
          console.error('Erreur de suppression:', err); // Ajoutez cette ligne
          this.errorMessage = 'Erreur suppression: ' + (err.error.message || err.message || 'Erreur inconnue');
        }
      });
    }
  }

  onSubmitNotifaciton(): void {
  this.loadData();
  const closeButton = document.getElementById('closeButton');
if (closeButton) {
  closeButton.click(); // This will simulate the click event and close the offcanvas
}
  }
  downloadFile(type:string,name:string){
    const url=`http://localhost:8082/api/download/${type}/${name}`;
    const link = document.createElement('a');
    link.href = url;
    link.download = name;  // Optional: Set the download attribute to suggest a file name
    link.click();
  }

}
