import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver';
import { CertifServiceService } from '../services/certif/certif-service.service';
import { AuthService } from '../services/Auth/auth.service';

@Component({
  selector: 'app-certif',
  templateUrl: './certif.component.html',
  styleUrls: ['./certif.component.css']
})
export class CertifComponent implements OnInit {
  certifs: any[] = [];
  users: any[] = [];
  selectedUser: string | null = null;
  loading = false;
  errorMessage = '';

  constructor(
    private certifService: CertifServiceService,
    private userService: AuthService
  ) {}

  ngOnInit() {
    this.loadUsers();
    this.loadCertifs();
  }

  loadCertifs() {
    this.loading = true;
    this.certifService.getCertifs().subscribe(
      (data: any[]) => {
        this.certifs = data;
        this.loading = false;
      },
      (error: any) => {
        this.errorMessage = 'Erreur lors du chargement des certificats';
        this.loading = false;
        console.error(error);
      }
    );
  }

  loadUsers() {
    this.loading = true;
    this.userService.getCurrentUser().subscribe(
      (users: any[]) => {
        this.users = users;
        this.loading = false;
      },
      (error: any) => {
        this.errorMessage = 'Erreur lors du chargement des utilisateurs';
        this.loading = false;
        console.error(error);
      }
    );
  }

  assignCertif(certifId: string) {
    if (!this.selectedUser) {
      alert('Veuillez sélectionner un utilisateur');
      return;
    }

    const certif = this.certifs.find(c => c.id === certifId);
    if (certif) {
      certif.assignedTo = this.selectedUser;
      const user = this.users.find(u => u.id === this.selectedUser);
      alert(`Certificat ${certif.fileName} assigné à ${user?.name}`);
      this.saveAssignmentLocally(certifId, this.selectedUser);
    }
  }

  private saveAssignmentLocally(certifId: string, userId: string) {
    const assignments = JSON.parse(localStorage.getItem('certifAssignments') || '{}');
    assignments[certifId] = userId;
    localStorage.setItem('certifAssignments', JSON.stringify(assignments));
  }

  getAssignedUserName(userId: string): string {
    if (!userId) return 'Non assigné';
    const user = this.users.find(u => u.id === userId);
    return user ? `${user.name}` : 'Utilisateur inconnu';
  }

  downloadCertif(idCertif: string, fileName: string) {
    this.loading = true;
    this.certifService.downloadCertif(idCertif).subscribe(
      (blob) => {
        saveAs(blob, `Certificat_${fileName}.pdf`);
        this.loading = false;
      },
      (error) => {
        this.errorMessage = 'Erreur lors du téléchargement';
        this.loading = false;
      }
    );
  }
}