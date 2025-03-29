import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Projet } from 'src/app/models/projet.model';
import { ProjetService } from 'src/app/projet/projet-service/project.service';
import * as bootstrap from 'bootstrap';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';
import { Chart, DoughnutController, ArcElement, Tooltip, Legend, Title } from 'chart.js';

// Correction: Enregistrement des composants corrects pour le doughnut chart
Chart.register(DoughnutController, ArcElement, Tooltip, Legend, Title);

@Component({
  selector: 'app-project-validation',
  templateUrl: './project-validation.component.html',
  styleUrls: ['./project-validation.component.css']
})
export class ProjectValidationComponent implements OnInit {
  projets: Projet[] = [];
  rejectionMotif: string = '';
  selectedProjet: Projet | null = null;
  encadrants: any[] = [];
  selectedEncadrant: string = '';
  searchEncadrant: string = '';
  

  // Statistiques des projets
  totalProjets: number = 0;
  projetsEnAttente: number = 0;
  projetsTermines: number = 0;

  @ViewChild('projectsChart') projectsChart!: ElementRef;

  constructor(
    private projetService: ProjetService,
    private userService: UserServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProjets();
    this.loadEncadrants();
  }

  loadProjets(): void {
    this.projetService.getAllProjets().subscribe((data) => {
      this.projets = data;
      this.calculateStats();
      this.createChart();
    });
  }
  loadEncadrants(): void {
    this.userService.getEncadrants().subscribe({
      next: (data) => {
        this.encadrants = data;
      },
      error: (err) => {
        console.error('Erreur de chargement des encadrants:', err);
        this.encadrants = [];
      }
    });
  }

  filteredEncadrants(): any[] {
    return this.encadrants.filter(e =>
      e.username.toLowerCase().includes(this.searchEncadrant.toLowerCase()) ||
      e.email.toLowerCase().includes(this.searchEncadrant.toLowerCase())
    );
  } 

  calculateStats(): void {
    this.totalProjets = this.projets.length;
    this.projetsEnAttente = this.projets.filter(projet => projet.statutProjet === 'EN_ATTENTE').length;
    this.projetsTermines = this.projets.filter(projet => projet.statutProjet === 'TERMINE').length;
  }

  createChart(): void {
    if (this.projectsChart) {
      const ctx = this.projectsChart.nativeElement.getContext('2d');
      const enCours = this.totalProjets - (this.projetsTermines + this.projetsEnAttente);

      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Terminés', 'En Attente', 'En Cours'],
          datasets: [{
            data: [this.projetsTermines, this.projetsEnAttente, enCours],
            backgroundColor: [
              this.createGradient(ctx, '#4BC0C0', '#36A2EB'),
              this.createGradient(ctx, '#FFCE56', '#FF9F40'),
              this.createGradient(ctx, '#9966FF', '#C9A0DC')
            ],
            borderColor: 'white',
            borderWidth: 3,
            hoverOffset: 20
          }]
        },
        options: {
          responsive: true,
          cutout: '65%',
          plugins: {
            legend: {
              position: 'bottom',
              labels: {
                padding: 20,
                font: { size: 14 },
                usePointStyle: true
              }
            },
            tooltip: {
              bodyFont: { size: 14 },
              displayColors: false,
              callbacks: {
                label: (context) => {
                  const value = context.parsed;
                  const percentage = ((value / this.totalProjets) * 100).toFixed(1);
                  return `${context.label}: ${value} (${percentage}%)`;
                }
              }
            }
          },
          animation: {
            animateRotate: true,
            animateScale: true
          }
        }
      });
    }
  }

  private createGradient(ctx: CanvasRenderingContext2D, color1: string, color2: string): CanvasGradient {
    const gradient = ctx.createLinearGradient(0, 0, 200, 0);
    gradient.addColorStop(0, color1);
    gradient.addColorStop(1, color2);
    return gradient;
  }
// Ajouter cette méthode dans ProjectValidationComponent
openRejectionModal(projet: Projet): void {
  this.selectedProjet = projet;
  const modalElement = document.getElementById('rejectionModal');
  if (modalElement) {
    const modal = new bootstrap.Modal(modalElement);
    modal.show();
    
    // Reset du motif quand le modal se ferme
    modalElement.addEventListener('hidden.bs.modal', () => {
      this.rejectionMotif = '';
    });
  }
}
  validateOrReject(id: string, isValid: boolean): void {
    if (!id) {
      alert('Aucun projet sélectionné!');
      return;
    }

    if (!isValid && !this.rejectionMotif) {
      alert('Veuillez entrer un motif de rejet.');
      return;
    }

    this.projetService.validateOrRejectProjet(id, isValid, isValid ? undefined : this.rejectionMotif)
    .subscribe({
      next: (updatedProjet) => { // Récupérez le projet mis à jour
        if (isValid) {
          this.selectedProjet = updatedProjet; // Utilisez le projet retourné
          this.showEncadrantModal();
        
          } else {
            this.loadProjets();
          }
          alert(`Projet ${isValid ? 'validé' : 'rejeté'} avec succès`);
          this.rejectionMotif = '';
        },
        error: (err) => {
          console.error('Erreur:', err);
          alert('Une erreur est survenue');
        }
      });
  }

  private showEncadrantModal(): void {
    const modalElement = document.getElementById('assignEncadrantModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
      
      modalElement.addEventListener('hidden.bs.modal', () => {
        this.selectedEncadrant = '';
        this.searchEncadrant = '';
        this.loadProjets();
      });
    }
  }

  assignEncadrantToProject(): void {
    if (!this.selectedProjet?.idProjet) {
      alert('Aucun projet sélectionné.');
      return;
    }

    if (!this.selectedEncadrant) {
      alert('Veuillez sélectionner un encadrant.');
      return;
    }

    this.projetService.assignEncadrant(this.selectedProjet.idProjet, this.selectedEncadrant)
      .subscribe({
        next: () => {
          alert('Encadrant assigné avec succès');
          const modalElement = document.getElementById('assignEncadrantModal')!;
          const modal = bootstrap.Modal.getInstance(modalElement);
          modal?.hide();
          this.loadProjets();
        },
        error: (err) => {
          console.error('Erreur:', err);
          alert('Erreur lors de l\'assignation');
        }
      });
  }

  

}
