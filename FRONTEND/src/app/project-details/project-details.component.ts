import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjetService } from '../services/project/project.service';
import { Projet } from '../Models/projet.model';
import { Router } from '@angular/router';
import { StatutProjet } from '../Models/projet.model';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent {
  projet: Projet | null = null;
  StatutProjet = StatutProjet;


  constructor(
    private route: ActivatedRoute,
    private projetService: ProjetService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const projetId = this.route.snapshot.paramMap.get('id');
    if (projetId) {
      this.loadProjetDetails(projetId);
    }
  }
  // Dans ProjectListComponent
  accederEspaceCollaboratif(projectId: string) {
    this.router.navigate(['/collaborative-space', projectId]);
  }
  loadProjetDetails(id: string): void {
    this.projetService.getProjetById(id).subscribe({
      next: (data) => {
        this.projet = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des détails du projet', err);
      }
    });
  }

  goBack(): void {
    window.history.back();  // Go back to the previous page
  }

}
