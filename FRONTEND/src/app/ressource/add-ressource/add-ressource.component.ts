import { ChangeDetectorRef, Component, EventEmitter, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ressource, TypeRessource, CategoryRessource } from 'src/app/models/ressource.model';
import { RessourceService } from 'src/app/ServicesRessource/ressource-service.service';
import { FileUploadService } from 'src/app/services/file-upload.service';

@Component({
  selector: 'app-add-ressource',
  templateUrl: './add-ressource.component.html',
  styleUrls: ['./add-ressource.component.css']
})
export class AddRessourceComponent {
  @Output() resourceAdded = new EventEmitter<Ressource>(); // Notify parent

  ressource: Ressource = {
     titre: '',
    description: '',
    type: TypeRessource.DOCUMENT, // Assurez-vous que ce type est défini dans votre modèle
    date: new Date(),
    category: CategoryRessource.OTHER,
    fileName:'', // Assurez-vous que cette catégorie est définie
  };
  types = Object.values(TypeRessource);
  categories = Object.values(CategoryRessource);
  
  successMessage: string = '';
  errorMessage: string = '';
  selectedFile: File | null = null;
  uploadMessage: string | null = null;
  constructor(private service: RessourceService,private fileUploadService:FileUploadService,    private cdr: ChangeDetectorRef // Inject ChangeDetectorRef
  ){

  }
  onSubmit(): void {
    console.log(this.ressource);
    this.service.create(this.ressource).subscribe({
      next: () => {
        this.successMessage = 'Ressource ajoutée avec succès';
        this.resetForm();
      this.resourceAdded.emit(); // Emit event to parent

      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Erreur lors de l\'ajout de la ressource';
      }
    });
  }
  
  resetForm(): void {
    this.ressource = { 
       titre: '', 
      description: '', 
      type: TypeRessource.DOCUMENT, 
      date: new Date(), 
      category: CategoryRessource.OTHER 
    };
    this.successMessage = '';
    this.errorMessage = '';
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] || null;
  }

  uploadFile() {
    if (!this.selectedFile) {
      this.uploadMessage = 'Please select a file first!';
      return;
    }

    this.fileUploadService.uploadFile(this.selectedFile, this.ressource.type).subscribe(
      (fileName) => {
        console.log(fileName)
        this.ressource.fileName = fileName;
        this.uploadMessage = 'File uploaded successfully!';
      },
      (error) => {
        this.uploadMessage = error.message;
      }
    );
  }
  onTypeChange() {
    console.log('Type changed to:', this.ressource.type);

    this.ressource.fileName = '';  // Reset the fileName
    this.selectedFile = null;      // Reset the selected file
     this.uploadMessage = null;     // Reset the upload message
    this.cdr.detectChanges();

  }
}
