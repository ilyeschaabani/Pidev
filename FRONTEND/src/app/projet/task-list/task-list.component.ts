import { Component, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Task, TaskStatus } from '../../models/task.model';
import { ChartConfiguration, ChartType } from 'chart.js';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TaskService } from '../task.service';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  filteredTasks: Task[] = [];
  todoTasks: Task[] = [];
  inProgressTasks: Task[] = [];
  doneTasks: Task[] = [];
  overdueTasks: Task[] = [];
  taskStatuses: string[] = ['To Do', 'In Progress', 'Done'];
  users: { id: number, name: string }[] = [
    { id: 1, name: 'John Doe' },
    { id: 2, name: 'Jane Smith' },
    // Add other users here
  ];
  
  filterForm: FormGroup;
  sortField = 'deadline';
  sortDirection = 'asc';

 
  // Chart configuration
  public progressChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: { display: true },
      tooltip: { enabled: true }
    }
  };
  public progressChartType: ChartType = 'doughnut';
  public progressChartData: any;

  constructor(
    private taskService: TaskService,
    private fb: FormBuilder
  ) {
    this.filterForm = this.fb.group({
      status: ['all'],
      assignedTo: ['all'],
      deadline: ['all']
    });
  }

  ngOnInit(): void {
    this.loadTasks();
    this.setupFilters();
    this.initChart();
  }
  
futureDateValidator(control: AbstractControl): ValidationErrors | null {
  const inputDate = new Date(control.value);
  const today = new Date();
  return inputDate < today ? { pastDate: true } : null;
}
  
  loadTasks(): void {
    this.taskService.getTasks().subscribe(tasks => {
      this.tasks = tasks;
      this.applyFilters();
      this.categorizeTasks();
      this.updateChart();
    });
  }

  categorizeTasks(): void {
    const now = new Date();
    this.todoTasks = this.filteredTasks.filter(t => t.status === TaskStatus.TO_DO);
    this.inProgressTasks = this.filteredTasks.filter(t => t.status === TaskStatus.IN_PROGRESS);
    this.doneTasks = this.filteredTasks.filter(t => t.status === TaskStatus.DONE);
    this.overdueTasks = this.filteredTasks.filter(t => 
      new Date(t.deadline) < now && t.status !== TaskStatus.DONE
    );
  }

  drop(event: CdkDragDrop<Task[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
      const task = event.container.data[event.currentIndex];
      this.updateTaskStatus(task, this.getStatusFromListId(event.container.id));
    }
  }

  private getStatusFromListId(listId: string): TaskStatus {
    switch(listId) {
      case 'todoList': return TaskStatus.TO_DO;
      case 'inProgressList': return TaskStatus.IN_PROGRESS;
      case 'doneList': return TaskStatus.DONE;
      default: return TaskStatus.TO_DO;
    }
  }

  updateTaskStatus(task: Task, status: TaskStatus): void {
    const updatedTask = { ...task, status };
    this.taskService.updateTask(updatedTask).subscribe({
      next: () => this.loadTasks(),
      error: (err) => console.error('Error updating task:', err)
    });
  }

  initChart(): void {
    this.progressChartData = {
      labels: ['To Do', 'In Progress', 'Done'],
      datasets: [{
        data: [0, 0, 0],
        backgroundColor: ['#ff6384', '#36a2eb', '#4bc0c0']
      }]
    };
  }

  updateChart(): void {
    const counts = {
      todo: this.todoTasks.length,
      inProgress: this.inProgressTasks.length,
      done: this.doneTasks.length
    };
    
    this.progressChartData.datasets[0].data = [
      counts.todo,
      counts.inProgress,
      counts.done
    ];
  }

  setupFilters(): void {
    this.filterForm.valueChanges.subscribe(() => this.applyFilters());
  }

  applyFilters(): void {
    let filtered = [...this.tasks];
    
    // Status filter
    if (this.filterForm.value.status !== 'all') {
      filtered = filtered.filter(t => t.status === this.filterForm.value.status);
    }

    // AssignedTo filter
    if (this.filterForm.value.assignedTo !== 'all') {
      filtered = filtered.filter(t => t.assignedTo === this.filterForm.value.assignedTo);
    }

    // Deadline filter
    if (this.filterForm.value.deadline === 'overdue') {
      const now = new Date();
      filtered = filtered.filter(t => new Date(t.deadline) < now);
    }

    this.filteredTasks = filtered;
    this.sortTasks();
  }

  sortTasks(): void {
    this.filteredTasks.sort((a, b) => {
      const fieldA = (a as any)[this.sortField];
      const fieldB = (b as any)[this.sortField];
      if (typeof fieldA === 'string' && typeof fieldB === 'string') {
        return this.sortDirection === 'asc' 
          ? fieldA.localeCompare(fieldB)
          : fieldB.localeCompare(fieldA);
      }
      
      return this.sortDirection === 'asc'
        ? fieldA - fieldB
        : fieldB - fieldA;
    });
  }

  setSort(field: string): void {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }
    this.sortTasks();
  }

  isOverdue(task: Task): boolean {
    return new Date(task.deadline) < new Date() && task.status !== TaskStatus.DONE;
  }
}