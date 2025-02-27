import { Component, OnInit } from '@angular/core';
import { ProjetService } from '../projet-service/project.service';
import { Projet, StatutProjet } from 'src/app/models/projet.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projets: Projet[] = [];
  StatutProjet = StatutProjet;
  newProjet: Projet = {
    idProjet: '',  // Un ID généré automatiquement peut être laissé vide
    titre: '',
    description: '',
    porteurProjet: '',
    encadrant: '',
    espaceCollaboratif: false, // Par défaut, pas d'espace collaboratif
    statutProjet: StatutProjet.EN_ATTEND // Statut initial du projet
  };

  constructor(private projetService: ProjetService, private http: HttpClient) { }

  ngOnInit(): void {
    this.loadProjets();
  }

  loadProjets(): void {
    this.projetService.getAllProjets().subscribe({
      next: (data) => {
        this.projets = data;
        console.log("Projets après suppression : ", this.projets);  // Vérifiez la liste des projets ici
      },
      error: (err) => {
        console.error('Erreur lors du chargement des projets', err);
      }
    });
  }
  

  onSubmit(): void {
    if (!this.newProjet.titre || !this.newProjet.description) return;
  
    // Créer une copie en Partial<Projet> pour éviter l'erreur avec delete
    const projetToSend: Partial<Projet> = { ...this.newProjet };
  
    // Supprime idProjet s'il existe (TypeScript ne bloquera pas)
    delete projetToSend.idProjet;
  
    this.projetService.addProjet(projetToSend).subscribe({
      next: () => {
        this.loadProjets();
        this.resetForm();
      },
      error: (err) => {
        console.error('Erreur lors de l’ajout du projet', err);
        alert(`Erreur : ${err.error.message}`);
      }
    });
  }
  
  
  // Fonction pour réinitialiser le formulaire
  resetForm() {
    this.newProjet = {
      idProjet: '', // Ne pas mettre de valeur par défaut
      titre: '',
      description: '',
      porteurProjet: '',
      encadrant: '',
      espaceCollaboratif: false,
      statutProjet: StatutProjet.EN_ATTEND
    };
  }
  
  supprimerProjet(idProjet?: string) {
    if (!idProjet) {
      console.error("ID du projet invalide");
      return;
    }
  
    this.projetService.deleteProjet(idProjet).subscribe({
      next: () => {
        console.log("Projet supprimé avec succès");
        // Rafraîchissez la liste des projets après la suppression
        this.loadProjets();
      },
      error: (error) => {
        console.error("Erreur lors de la suppression du projet", error);
        alert("Erreur lors de la suppression du projet");
      }
    });
  }
  
  
  modifierProjet(projet: any) {
    // Ajouter votre logique pour modifier un projet ici
    console.log("Modifier projet:", projet);
  }
  
  
}
