export enum TaskStatus {
    TO_DO = 'TO_DO',
    IN_PROGRESS = 'IN_PROGRESS',
    DONE = 'DONE'
  }
  
  export interface Task {
    id: string;
    projectId: string;
    title: string;
    description?: string;
    status: TaskStatus;
    deadline: Date;
    assignedTo?: string;
    deliverables?: string[];
    createdAt: Date;
    updatedAt: Date;
  }