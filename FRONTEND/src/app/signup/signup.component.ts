import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from "../services/Auth/auth.service";
import { Role } from '../Models/User.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  signupForm: FormGroup;
  message: string = '';
  roles: Role[] = ['ETUDIANT', 'CONSULTANT', 'ENCADRANT', 'ADMIN'];
    // ... existing properties ...
    selectedFile: File | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    console.log('SignupComponent constructor called');
    this.signupForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      adresse: ['', Validators.required],
      telephone: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      //image: [null, Validators.required],  // Change to accept file

      role: ['', Validators.required]  // Change to single role
    });
    console.log('Signup form initialized', this.signupForm);
  }
    // Add this method
    onFileSelected(event: any) {
      this.selectedFile = event.target.files[0];
    }

  onSubmit() {
    console.log('onSubmit called');
    if (this.signupForm.valid ) {
      console.log('Signup form is valid', this.signupForm.value);
      console.log('Request payload:', this.signupForm.value);
      this.authService.signUp(this.signupForm.value).subscribe({
        next: () => {
          console.log('Sign up successful');
          this.message = 'Inscription réussie ! Redirection...';
          setTimeout(() => {
            this.router.navigate(['/login']); // Redirection après 2 secondes
          }, 2000);
        },
        error: err => {
          console.error('Sign up error', err);
          this.message = 'Erreur lors de l\'inscription';
        }
      });
    } else {
      console.warn('Signup form is invalid', this.signupForm);
      this.logFormErrors();
    }
  }

  private logFormErrors() {
    Object.keys(this.signupForm.controls).forEach(key => {
      const controlErrors = this.signupForm.get(key)?.errors;
      if (controlErrors) {
        console.error(`Form control ${key} is invalid:`, controlErrors);
      }
    });
  }
}