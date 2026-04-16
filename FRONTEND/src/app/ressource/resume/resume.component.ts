import { Component, Input, OnInit  } from '@angular/core';
import { RessourceService } from '../../services/resource/ressource-service.service';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent {
  @Input() fileName!: String;
  resume = "";

  constructor(private resourceService: RessourceService) {}

  ngOnInit(): void {
    this.resourceService.getResume(this.fileName).subscribe((data) => {
      this.resume = data.summary;
    });
  }

  // Méthode pour copier le résumé dans le presse-papiers
  copyResumeToClipboard(): void {
    navigator.clipboard.writeText(this.resume).then(() => {
      alert("Résumé copié dans le presse-papiers !");
    }).catch((err) => {
      console.error("Erreur lors de la copie :", err);
    });
  }

}
