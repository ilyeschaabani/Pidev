export interface DocumentFile {
  fileName: string;
  filePath: string;
}


export interface Comment {
  text: string;
  date: Date;
  username: string;
}

export interface Evaluation {
  progressScore: number;
  finalScore : number;
  feedback: string; // Ajout de la propriété feedback
}
export interface PFEProject {
  id?: string;
  title: string;
  description: string;
  studentIds: string[];
  mentorId: string;
  stage: 'PROPOSAL' | 'IN_PROGRESS' | 'COMPLETED';
  documents?: any[]; // adapte les types selon tes besoins
  comments?: any[];
  evaluation?: any;
  createdAt?: Date;
  updatedAt?: Date;
}

export interface User {
  id: string;
  username: string;
}
