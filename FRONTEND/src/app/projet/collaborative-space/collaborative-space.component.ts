import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileService } from '../../files/file-service.service';
import { ProjetService } from '../projet-service/project.service';
import { ProjectFile } from '../../models/project-file.model';
import { Milestone } from 'src/app/models/Milestone.model';
@Component({
  selector: 'app-collaborative-space',
  templateUrl: './collaborative-space.component.html',
  styleUrls: ['./collaborative-space.component.css']
})

export class CollaborativeSpaceComponent implements OnInit {
  projectId!: string;
  newFile: File | null = null;
  files: ProjectFile[] = [];
  isLoading = false;
  milestones: Milestone[] = [];

  constructor(
    private route: ActivatedRoute,
    private fileService: FileService,
    private projetService: ProjetService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initializeComponent();
  }

  private initializeComponent(): void {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    if (!this.projectId) {
      console.error('No project ID found');
      this.router.navigate(['/projects']);
      return;
    }
    
    // Load both files and milestones
    this.loadFiles();
    this.loadMilestones();
  }
  
  loadFiles(): void {
    this.isLoading = true;
  
    this.fileService.getProjectFiles(this.projectId).subscribe({
      next: (files) => {
        if (files.length === 0) {
          console.warn('Aucun fichier trouvé pour ce projet');
        }
        this.files = files;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur détaillée:', err.error);
        this.isLoading = false;
        this.handleError(err);
      }
    });
  }
  
  private handleError(error: any): void {
    if (error.status === 400) {
      console.error('Erreur de requête:', error.error.message);
      this.router.navigate(['/projects']);
    } else {
      console.error('Erreur inattendue:', error.message);
    }
  }
  onFileSelected(event: any) {
    this.newFile = event.target.files[0];
  }

  uploadFile() {
    if (this.newFile) {
      const formData = new FormData();
      formData.append('file', this.newFile);
      
      this.fileService.uploadFile(this.projectId, formData).subscribe({
        next: () => {
          this.loadFiles();
          this.newFile = null;
        },
        error: (err) => console.error(err)
      });
    }
  }

  downloadFile(fileId: string, fileName: string) {
    this.fileService.downloadFile(fileId).subscribe(blob => {
      const a = document.createElement('a');
      const objectUrl = URL.createObjectURL(blob);
      a.href = objectUrl;
      a.download = fileName;
      a.click();
      URL.revokeObjectURL(objectUrl);
    });
  }

  

  loadMilestones(): void {
    this.isLoading = true;
    this.projetService.getProjectMilestones(this.projectId).subscribe({
      next: (milestones) => {
        if (milestones.length === 0) {
          console.warn('Aucune milestone trouvée pour ce projet');
        }
        this.milestones = milestones;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur détaillée:', err.error);
        this.isLoading = false;
        this.handleError(err);
      }
    });
  }

}