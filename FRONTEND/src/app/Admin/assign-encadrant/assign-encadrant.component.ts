import { Component, OnInit } from '@angular/core';
import { ProjetService } from 'src/app/projet/projet-service/project.service';
import { UserServiceService } from '../user-service.service'; 
import { Projet } from 'src/app/models/projet.model';

@Component({
  selector: 'app-assign-encadrant',
  templateUrl: './assign-encadrant.component.html',
  styleUrls: ['./assign-encadrant.component.css']
})
export class AssignEncadrantComponent implements OnInit {
  projets: Projet[] = [];
  encadrants: any[] = [];
  selectedProjetId: string = '';
  selectedEncadrantId: string = '';

  constructor(
    private projetService: ProjetService,
    private userService: UserServiceService
  ) {}

  ngOnInit(): void {
    this.loadProjets();
    this.loadEncadrants();
  }

  loadProjets(): void {
    this.projetService.getAllProjets().subscribe((data) => {
      this.projets = data.filter(projet => projet.statutProjet === 'TERMINE');
    });
  }

  loadEncadrants(): void {
    this.userService.getEncadrants().subscribe((data) => {
      this.encadrants = data;
    });
  }

  // Fonction pour obtenir le titre du projet sélectionné
  getProjetTitle(projetId: string): string {
    const projet = this.projets.find(p => p.idProjet === projetId);
    return projet ? projet.titre : '';
  }

  assignEncadrant(): void {
    if (!this.selectedProjetId || !this.selectedEncadrantId) {
      alert('Veuillez sélectionner un projet et un encadrant.');
      return;
    }

    this.projetService.assignEncadrant(this.selectedProjetId, this.selectedEncadrantId).subscribe(() => {
      alert('Encadrant assigné avec succès !');
      this.loadProjets(); // Recharger la liste des projets
    });
  }
}
