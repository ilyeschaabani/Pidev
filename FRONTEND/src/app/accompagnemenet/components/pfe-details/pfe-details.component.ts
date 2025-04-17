import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PfeProjectService } from '../../Services/pfe-project.service';
import { PFEProject } from '../../models/pfe-project.model';

@Component({
  selector: 'app-pfe-details',
  templateUrl: './pfe-details.component.html',
  styleUrls: ['./pfe-details.component.css']
})
export class PfeDetailsComponent implements OnInit {
  selectedProject: PFEProject | null = null;
  selectedFile: File | null = null;
  uploadStatus: { success: boolean; error: boolean } | null = null;

  constructor(
    private route: ActivatedRoute,
    private projectService: PfeProjectService
  ) {}

  ngOnInit(): void {
    const projetId = this.route.snapshot.paramMap.get('id');
    if (projetId) {
      this.loadProjectDetails(projetId);
    }
  }

  loadProjectDetails(id: string): void {
    this.projectService.getProjectById(id).subscribe({
      next: (data) => {
        this.selectedProject = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement du projet', err);
      }
    });
  }

  onFileChange(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onFileUpload(): void {
    if (!this.selectedFile || !this.selectedProject?.id) {
      return;
    }
  
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('currentUsername', 'username-placeholder'); // Remplacer par l'username réel
  
    this.projectService.uploadDocument(this.selectedProject.id, formData).subscribe(
      (response) => {
        this.uploadStatus = { success: true, error: false };
        // Mettre à jour les détails du projet pour inclure les documents téléchargés
        this.selectedProject = response;  // Mettez à jour le projet avec la liste des documents
      },
      (error) => {
        this.uploadStatus = { success: false, error: true };
      }
    );
  }
  checkDocumentUrl(url: string): void {
    console.log('Document URL:', url);
    // Si l'URL du document est valide, vous pouvez l'ouvrir dans un nouvel onglet
    if (!url) {
      console.error('L\'URL du document est invalide.');
    }
  }

  goBack(): void {
    window.history.back();  // Go back to the previous page
  }
}
