import { Component, OnInit } from '@angular/core';
import { ProjetService } from 'src/app/projet/projet-service/project.service';  // Assurez-vous de la bonne importation du service
import { Projet,StatutProjet  } from 'src/app/models/projet.model';
@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  projets: Projet[] = [];
  StatutProjet =StatutProjet ;
  constructor(private projetService: ProjetService) { }

  ngOnInit(): void {
    this.loadProjets();
  }

  loadProjets(): void {
    this.projetService.getAllProjets().subscribe((data) => {
      this.projets = data;
    });
  }
}
