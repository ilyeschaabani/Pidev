import { Component } from '@angular/core';
import { ProjetService } from '../services/project/project.service';
import { AuthService } from '../services/Auth/auth.service';
import { Projet } from '../Models/projet.model';


@Component({
  selector: 'app-assign-encadrant',
  templateUrl: './assign-encadrant.component.html',
  styleUrls: ['./assign-encadrant.component.css']
})
export class AssignEncadrantComponent {
  projets: Projet[] = [];
  encadrants: any[] = [];
  selectedProjetId: string = '';
  selectedEncadrantId: string = '';

  constructor(
    private projetService: ProjetService,
    private userService:  AuthService
  ) {}

  ngOnInit(): void {
    this.loadProjets();
    this.loadEncadrants();
  }

  loadProjets(): void {
    this.projetService.getAllProjets().subscribe((data) => {
      this.projets = data.filter(projet => projet.statutProjet === 'EN_COURS');
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
