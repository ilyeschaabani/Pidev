import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private baseUrl = 'http://localhost:8082/api/users';

  constructor(private http: HttpClient) {}


  getEncadrants() {
    return this.http.get<any[]>(`${this.baseUrl}/encadrants`);
  }
}
