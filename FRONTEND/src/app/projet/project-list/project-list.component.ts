import { Component, OnInit } from '@angular/core';
import { ProjetService } from '../projet-service/project.service';
import { Projet, StatutProjet } from 'src/app/models/projet.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'; // Importer Router pour la navigation
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
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

  // Fonction pour afficher la boîte de dialogue de confirmation
  confirmDelete(id: string): void {
    this.selectedProjet = this.projets.find(projet => projet.idProjet === id) || null;
    if (this.selectedProjet) {
      const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal')!);
      modal.show();
    } else {
      console.error("Projet non trouvé pour l'ID:", id);
    }
  }
  
 

  // Fonction pour supprimer un projet après confirmation
supprimerProjet(): void {
  if (this.selectedProjet) {
    this.projetService.deleteProjet(this.selectedProjet.idProjet).subscribe({
      next: () => {
        console.log('Projet supprimé');
        alert('Projet supprimé avec succès'); // Message de succès

        // Recharger la liste après suppression
        this.loadProjets();

        // Fermer la modal après la suppression
        const modalElement = document.getElementById('confirmDeleteModal');
        if (modalElement) {
          const modal = new bootstrap.Modal(modalElement);
          modal.hide(); // Ferme la modal
        }
      },
      error: (err) => {
        console.error('Erreur lors de la suppression du projet', err);
        alert('Erreur lors de la suppression du projet'); // Message d'erreur
      }
    });
  }
}

  modifierProjet(projet: Projet): void {
    // Implémenter la logique pour modifier le projet
  }
}
