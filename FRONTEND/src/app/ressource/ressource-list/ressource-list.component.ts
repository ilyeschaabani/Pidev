import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Assurez-vous d'importer HttpClient 
import { CategoryRessource, Ressource, TypeRessource } from '../../models/ressource.model';
import { RessourceService } from '../../ServicesRessource/ressource-service.service';
 
@Component({
  selector: 'app-ressource-list',
  templateUrl: './ressource-list.component.html',
  styleUrls: ['./ressource-list.component.css']
})
export class RessourceListComponent implements OnInit {
  ressources: Ressource[] = [];
 
  types = Object.values(TypeRessource);
  categories = Object.values(CategoryRessource);
  selectedFileName:String="";
  selectedResource:Ressource|null=null;
   successMessage: string = '';
  errorMessage: string = '';
  @ViewChild('exampleModal', { static: false }) modal!: ElementRef;

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
if (closeButton instanceof HTMLElement) {
  closeButton.click(); // This will simulate the click event and close the offcanvas
}
  this.selectedResource=null;

  }
  downloadFile(type:string,name:string){
    const url=`http://localhost:8082/api/download/${type}/${name}`;
    const link = document.createElement('a');
    link.href = url;
    link.download = name;  // Optional: Set the download attribute to suggest a file name
    link.click();
  }
  isApdfWordDocument(fileName:string){
    const fileExtension = fileName.toLowerCase();

    // Check if the file ends with .pdf or .docx
    return fileExtension.endsWith('.pdf') || fileExtension.endsWith('.docx');
  
  }
  resumeFile(fileName:string){
    this.selectedFileName=fileName
  }
  onModifyClick(resource:Ressource){
    this.selectedResource=resource;
  }
 
 

}
