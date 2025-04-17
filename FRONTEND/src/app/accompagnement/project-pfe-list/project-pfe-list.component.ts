import { Component } from '@angular/core';
import { PfeProjectService } from '../../services/pfe-project/pfe-project.service';
import { PFEProject } from '../../Models/pfe-project.model';
import * as bootstrap from 'bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-pfe-list',
  templateUrl: './project-pfe-list.component.html',
  styleUrls: ['./project-pfe-list.component.css']
})
export class ProjectPfeListComponent {
  projets: PFEProject[] = [];
  selectedProject: PFEProject | null = null;

  newProject: PFEProject = this.getEmptyProject();

  keywords: string = '';
  generatedTopics: string[] = [];

  constructor(private projectService: PfeProjectService, private router: Router) {}

  ngOnInit(): void {
    this.fetchProjects();
  }

  viewDetails(projectId: string): void {
    this.router.navigate(['/project-details', projectId]);
  }

  editProject(project: PFEProject): void {
    this.newProject = { ...project }; // Remplir le formulaire avec les données du projet sélectionné
    const offcanvas = document.getElementById('offcanvasAddProject');
    if (offcanvas) {
      new bootstrap.Offcanvas(offcanvas).show();
    }
  }

  deleteProject(id: string): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce projet ?')) {
      this.projectService.deleteProject(id).subscribe(() => {
        this.fetchProjects();
      });
    }
  }

  fetchProjects(): void {
    this.projectService.getProjects().subscribe((projects) => {
      this.projets = projects;
    });
  }

  createProject(): void {
    if (!this.newProject.title || !this.newProject.description || !this.newProject.mentorId) return;

    // Si un ID est présent => c'est une mise à jour
    if (this.newProject.id && this.newProject.id.trim() !== '') {
      this.projectService.updateProject(this.newProject.id, this.newProject).subscribe(() => {
        this.fetchProjects();
        this.resetForm();
      });
    } else {
      // Création d’un nouveau projet
      const projectToCreate = { ...this.newProject };
      delete projectToCreate.id; // on s'assure que l'id est bien vide
      this.projectService.createProject(projectToCreate).subscribe(() => {
        this.fetchProjects();
        this.resetForm();
      });
    }
  }

  resetForm(): void {
    this.newProject = this.getEmptyProject();
    // Optionnel : fermer l'offcanvas après soumission
    const offcanvasEl = document.querySelector('.offcanvas.show') as HTMLElement;
    if (offcanvasEl) {
      const offcanvas = bootstrap.Offcanvas.getInstance(offcanvasEl);
      offcanvas?.hide();
    }
  }

  getEmptyProject(): PFEProject {
    return {
      id: '',
      title: '',
      description: '',
      studentIds: [],
      mentorId: '',
      stage: 'PROPOSAL',
      documents: [],
      comments: [],
      evaluation: null
    };
  }

  onStudentIdsChange(value: string): void {
    this.newProject.studentIds = value
      .split(',')
      .map(id => id.trim())
      .filter(id => id);
  }
}
