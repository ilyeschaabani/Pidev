import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private url = "https://quizapi.io/api/v1/question ";

  constructor(private http:HttpClient) { }

  public getQuizQuestions(difficulty:string,limit:number):Observable<any>{
    let headers = { 'X-Api-Key' : environment.quizApiKey };
    return this.http.get(`${this.url}?difficulty=${difficulty}&limit=${limit}`,{ headers:headers });
  }

}
