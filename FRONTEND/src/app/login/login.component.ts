import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthService) { }
  login() {
    this.authService.signin({ username: this.username, password: this.password }).subscribe(
      (response: any) => {
        this.authService.saveToken(response.token);
        alert('Login successful!');
      },
      error => {
        alert('Invalid credentials');
      }
    );
  }

}
