import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8089/AuthenticationMicroService/api/auth'; // API Gateway

  constructor(private http: HttpClient) { }

  getHeaders() {
    const token = this.getToken();
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : new HttpHeaders();
  }
  signup(user: any) {
    return this.http.post(`${this.apiUrl}/signup`, user);
  }
  signin(credentials: any) {
    return this.http.post(`${this.apiUrl}/signin`, credentials);
  }
  refreshToken(refreshToken: string) {
    return this.http.post(`${this.apiUrl}/refreshToken`, { refreshToken });
  }
  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('token');
  }
}
