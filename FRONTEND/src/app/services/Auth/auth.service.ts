import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';
import { SignUpRequest, SignInRequest, AuthResponse, User } from "../../Models/User.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:9006/api/auth'; // API Gateway

  constructor(private http: HttpClient) {}

  // Signup
  signUp(user: SignUpRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/signup`, user);
  }

  // Signin
  signIn(credentials: SignInRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/signin`, credentials).pipe(
      tap((response: AuthResponse) => {
        console.log('AuthResponse received:', response);

        localStorage.setItem('token', response.token);

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

  // Logout
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  // Check Auth
  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // Get Current User from Storage
  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  // Get Current User from API
  getCurrentUser(): Observable<any> {
    const token = localStorage.getItem('token');
    if (!token) return of(null);

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get<any>(`${this.apiUrl}/me`, { headers }).pipe(
      tap(user => {
        localStorage.setItem('user', JSON.stringify(user));
      })
    );
  }

  // Get All Users
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/getuser`);
  }

  // Delete User by ID
  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // Update User
  updateUser(id: string, updatedUser: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/update/${id}`, updatedUser);
  }

  // Get only Encadrants
  getEncadrants(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/encadrants`);
  }
}
