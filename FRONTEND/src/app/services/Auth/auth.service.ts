import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SignUpRequest, SignInRequest, AuthResponse } from "../../Models/User.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9006/api/auth'; // API Gateway

  constructor(private http: HttpClient) {}

  signUp(user: SignUpRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/signup`, user);
  }

  signIn(credentials: SignInRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/signin`, credentials).pipe(
      tap((response: AuthResponse) => {
        console.log('AuthResponse received:', response);
  
        // Store token in localStorage
        localStorage.setItem('token', response.token);
  
        // Call /me to get user info
        const headers = new HttpHeaders({
          Authorization: `Bearer ${response.token}`
        });
  
        this.http.get<any>(`${this.apiUrl}/me`, { headers }).subscribe({
          next: (user) => {
            localStorage.setItem('user', JSON.stringify(user));
            console.log('User information fetched from /me and saved:', user);
          },
          error: (err) => {
            console.error('Failed to fetch user from /me:', err);
          }
        });
      })
    );
  }
  

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getUser(): any {
    const user = localStorage.getItem('user');
    console.log('Retrieved user from localStorage:', user);
    return user ? JSON.parse(user) : null;
  }
  getCurrentUser(): Observable<any> {
    const token = localStorage.getItem('token');
    if (!token) return new Observable(); // return empty observable if no token
  
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  
    return this.http.get<any>(`${this.apiUrl}/me`, { headers }).pipe(
      tap(user => {
        localStorage.setItem('user', JSON.stringify(user));
      })
    );
  }
  
  


}