import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/Auth/auth.service';
import { User } from 'src/app/Models/User.model';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user: User = {
    idUser: '',
    nom: '',
    prenom: '',
    email: '',
    roles: [],
    telephone: '',
    adresse: '',
    password: ''
  };
  userId: string = '';

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id') || '';
    this.loadUser();
  }

  loadUser(): void {
    this.authService.getAllUsers().subscribe(
      (users: any[]) => {
        const foundUser = users.find((user: { idUser: string; }) => user.idUser === this.userId);
        if (foundUser) {
          this.user = foundUser;
        }
      },
      (error: any) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.user.idUser) {
      this.authService.updateUser(this.user.idUser, this.user).subscribe(
        (updatedUser: any) => {
          console.log('User updated successfully:', updatedUser);
          this.router.navigate(['/admin/users']); // Redirect to the user list page
        },
        (error: any) => {
          console.error('Error updating user:', error);
        }
      );
    }
  }
}
