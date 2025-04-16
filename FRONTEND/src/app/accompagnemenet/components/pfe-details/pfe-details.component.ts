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

  goBack(): void {
    window.history.back();  // Go back to the previous page
  }
}
