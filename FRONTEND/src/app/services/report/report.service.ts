import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private apiUrl = 'http://localhost:9009/api/reports'; // Remplace avec ton URL

  constructor(private http:HttpClient) { }
   addReport(report: any) {
      return this.http.post<any>(`${this.apiUrl}`, report);
    }
}
