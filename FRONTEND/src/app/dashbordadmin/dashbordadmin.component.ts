import { Component } from '@angular/core';

import { User } from '../Models/User.model';
import { AuthService } from '../services/Auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashbordadmin',
  templateUrl: './dashbordadmin.component.html',
  styleUrls: ['./dashbordadmin.component.css']
})
export class DashbordadminComponent {
   user: User | undefined;
    isLoggedIn: boolean = false; 
  
    constructor(
      public authService: AuthService,
      private router: Router
    ) {}
  
    ngOnInit() {
      this.isLoggedIn = this.authService.isAuthenticated();
  
      if (this.isLoggedIn) {
        this.authService.getCurrentUser().subscribe({
          next: (userData) => {
            console.log('User data:', userData);
            this.user = userData;
          },
          error: () => {
            this.authService.logout(); // token might be invalid
            this.isLoggedIn = false;
          }
        });
      }
    }
    logout() {
      this.authService.logout();
      this.router.navigate(['/login']);
    }

}
