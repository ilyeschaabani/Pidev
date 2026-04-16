import { Component } from '@angular/core';
import { ProjetService } from '../services/project/project.service';
import { Projet, StatutProjet } from '../Models/projet.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; // Importer Router pour la navigation
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-projectlist',
  templateUrl: './projectlist.component.html',
  styleUrls: ['./projectlist.component.css']
})
export class ProjectlistComponent {
  projets: Projet[] = [];
  selectedProjet: Projet | null = null;  // Variable pour stocker le projet sélectionné
  StatutProjet = StatutProjet;
  newProjet: Projet = {
    idProjet: '',  // ID vide pour l'ajout
    titre: '',
    description: '',
    porteurProjet: '',
    encadrant: '',
    espaceCollaboratif: false,
    statutProjet: StatutProjet.EN_ATTENTE, // Statut initial
  };
  idProjetASupprimer: string | null = null;

  constructor(private projetService: ProjetService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.loadProjets();
  }


  loadProjets(): void {
    this.projetService.getAllProjets().subscribe({
      next: (data) => {
        this.projets = data;
        console.log("Projets chargés : ", this.projets);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des projets', err);
      }
    });
  }
  allerPrediction(): void {
    // Redirige vers la page prédiction
    this.router.navigate(['/PredictProject']);
  }
  
  onSubmit(): void {
    if (!this.newProjet.titre || !this.newProjet.description) return;

    const projetToSend: Partial<Projet> = { ...this.newProjet };
    delete projetToSend.idProjet; // L'ID est généré automatiquement côté backend

    this.projetService.addProjet(projetToSend).subscribe({
      next: (newProjet) => {
        console.log("Projet ajouté : ", newProjet);
        this.loadProjets();  // Recharger la liste après ajout
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout du projet', err);
      }
    });
  }

  afficherDetailsProjet(id: string): void {
    this.router.navigate(['/projet', id]); // Navigate to project details page using project ID
  }

  confirmDelete(id: string) {
    this.idProjetASupprimer = id;
    const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal')!);
    modal.show();
  }

  supprimerProjet() {
    if (this.idProjetASupprimer) {
      this.projetService.deleteProjet(this.idProjetASupprimer).subscribe(() => {
        this.projets = this.projets.filter(p => p.idProjet !== this.idProjetASupprimer);
        this.idProjetASupprimer = null;

        const modal = bootstrap.Modal.getInstance(document.getElementById('confirmDeleteModal')!);
        modal?.hide();
      });
    }
  }
  

modifierProjet(projet: any) {
  this.newProjet = { ...projet }; // Préremplit le formulaire dans le offcanvas
  const offcanvas = new bootstrap.Offcanvas(document.getElementById('offcanvasAddProject')!);
  offcanvas.show();
}

}
