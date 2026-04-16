import { Component } from '@angular/core';
import { AuthService } from '../services/Auth/auth.service';
import { Router } from '@angular/router';
import { User } from '../Models/User.model';

@Component({
  selector: 'app-useradmine',
  templateUrl: './useradmine.component.html',
  styleUrls: ['./useradmine.component.css']
})
export class UseradmineComponent {
  users: User[] = [];

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(): void {
    this.authService.getAllUsers().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Failed to load users', err);
      }
    });
  }

  navigateToAddUser(): void {
    this.router.navigate(['/signup']); // adapt route to your app
  }

  editUser(id: string): void {
    this.router.navigate(['/edituseradmin', id]);
  }

  deleteUser(id: string): void {
    if (confirm('Voulez-vous vraiment supprimer cet utilisateur ?')) {
      this.authService.deleteUser(id).subscribe({
        next: () => {
          this.users = this.users.filter(user => user.idUser !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression', err);
        }
      });
    }
  }

}
