import { Component, OnInit } from '@angular/core';
import { PfeProjectService } from '../../Services/pfe-project.service';
import { PFEProject } from '../../models/pfe-project.model';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-projet-pfe-list',
  templateUrl: './projet-pfe-list.component.html',
  styleUrls: ['./projet-pfe-list.component.css']
})
export class ProjetPfeListComponent implements OnInit {
  projets: PFEProject[] = [];
  selectedProject: PFEProject | null = null;

  newProject: PFEProject = {
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

  keywords: string = '';
  generatedTopics: string[] = [];

  constructor(private projectService: PfeProjectService) {}

  ngOnInit(): void {
    this.fetchProjects();
  }

  viewDetails(project: PFEProject): void {
    this.selectedProject = project;
    alert(`Détails du projet:\nTitre: ${project.title}\nDescription: ${project.description}\nMentor: ${project.mentorId}\nStatut: ${project.stage}`);
  }

  editProject(project: PFEProject): void {
    this.newProject = { ...project };
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
    if (!this.newProject.title || !this.newProject.description) return;
  
    if (this.newProject.id) {
      this.projectService.updateProject(this.newProject.id, this.newProject).subscribe(() => {
        this.fetchProjects();
        this.resetForm();
      });
    } else {
      this.projectService.createProject(this.newProject).subscribe(() => {
        this.fetchProjects();
        this.resetForm();
      });
    }
  }

  resetForm(): void {
    this.newProject = {
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
