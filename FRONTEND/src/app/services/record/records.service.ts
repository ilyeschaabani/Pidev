import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

// Define or import the apiUrl
  
const apiUrl = 'http://localhost:8093/evaluations'; // URL de ton backend

@Injectable({
  providedIn: 'root'
})
export class RecordsService {

  constructor(
    private _record:HttpClient
  ) { }

  // add record 
  public addRecord(record:any){
    return this._record.post(`${apiUrl}/user-activities/`, record);
  }

  // update record
  public updateRecord(record:any){
    return this._record.put(`${apiUrl}/user-activities/`, record);
  }

  // get all records
  public getAllRecords(){
    return this._record.get(`${apiUrl}/user-activities/`);
  }

  // get one record
  public getSingleRecord(aId:any){
    return this._record.get(`${apiUrl}/user-activities/${aId}`);
  }

  // delete record
  public deleteRecord(aId:any){
    return this._record.delete(`${apiUrl}/user-activities/${aId}`);
  }

  // get records of quiz
  public getRecordsOfQuiz(qId:any){
    return this._record.get(`${apiUrl}/user-activities/quiz/${qId}`);
  }

  // get records of user
  public getRecordsOfUser(username:any){
    return this._record.get(`${apiUrl}/user-activities/user/${username}`);
  }
}
