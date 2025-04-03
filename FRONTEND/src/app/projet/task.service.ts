// task.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Task, TaskStatus } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8082/api/projets'; // Adapter selon votre configuration

  constructor(
    private http: HttpClient,
  ) { }

  // Récupérer toutes les tâches d'un projet
  getProjectTasks(projectId: string): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.baseUrl}/${projectId}/tasks`).pipe(
      map(tasks => this.mapTasksDates(tasks)),
      catchError(this.handleError('Erreur lors du chargement des tâches'))
    );
  }

  // Créer une nouvelle tâche
  createTask(projectId: string, task: Partial<Task>): Observable<Task> {
    return this.http.post<Task>(`${this.baseUrl}/${projectId}/tasks`, task).pipe(
      map(this.mapTaskDates),
      catchError(this.handleError("Erreur lors de la création de la tâche"))
    );
  }

  // Mettre à jour le statut d'une tâche (pour le drag-and-drop)
  updateTaskStatus(taskId: string, status: TaskStatus): Observable<Task> {
    return this.http.put<Task>(`${this.baseUrl}/tasks/${taskId}/status`, { status }).pipe(
      map(this.mapTaskDates),
      catchError(this.handleError("Erreur lors de la mise à jour du statut"))
    );
  }

  // Mettre à jour une tâche complète
  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(`${this.baseUrl}/tasks/${task.id}`, task).pipe(
      map(this.mapTaskDates),
      catchError(this.handleError("Erreur lors de la mise à jour de la tâche"))
    );
  }

  // Supprimer une tâche
  deleteTask(taskId: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/tasks/${taskId}`).pipe(
      catchError(this.handleError("Erreur lors de la suppression de la tâche"))
    );
  }

  // Gestion centralisée des erreurs
  private handleError(operation = 'operation') {
    return (error: any) => {
      console.error(`${operation} failed:`, error);
      return throwError(() => new Error(error));
    };
  }

  // Mapper les dates des tâches
  private mapTasksDates(tasks: Task[]): Task[] {
    return tasks.map(task => this.mapTaskDates(task));
  }

  private mapTaskDates(task: Task): Task {
    return {
      ...task,
      deadline: new Date(task.deadline),
      createdAt: new Date(task.createdAt),
      updatedAt: new Date(task.updatedAt)
    };
  }
  getTasks(projectId?: string): Observable<Task[]> {
    return this.http.get<Task[]>(`/api/tasks?projectId=${projectId || ''}`);
  }
  

}