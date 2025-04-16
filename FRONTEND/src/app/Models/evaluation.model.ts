import { Question } from "./question.model";

export interface Evaluation {
  duree: number;
dateCreation: string|number|Date;
  idEvaluation: string;
  titre: string;
  note: number;
  description: string;
  maxMarks: string; // Correspond au type String dans le backend
  noOfQuestions: string; // Correspond au type String dans le backend
  active: boolean;
  quesId?: number; // Optionnel car Long en Java peut être null
  questions?: Question[];
  quizRecords?: any[]; // Vous pouvez créer une interface spécifique si nécessaire
  
  // Champs optionnels supplémentaires
  dateFin?: Date;
  dateDebut?: Date;
  scoreMaximum?: number;
  createdAt?: string;
  statut?: string;
}