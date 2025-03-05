// project-details.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjetService } from '../projet-service/project.service';
import { Projet } from 'src/app/models/projet.model';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  projet: Projet | null = null;

  constructor(
    private route: ActivatedRoute,
    private projetService: ProjetService
  ) { }

  ngOnInit(): void {
    const projetId = this.route.snapshot.paramMap.get('id');
    if (projetId) {
      this.loadProjetDetails(projetId);
    }
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
