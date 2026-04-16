// models/projet.model.ts
export enum StatutProjet {
  EN_ATTENTE = 'EN_ATTENTE',
  EN_COURS = 'EN_COURS',
  TERMINE = 'TERMINE',
  REJETE = 'REJETÉ'  // Add this value

}


export interface Projet {
  idProjet: string;  // L'ID est maintenant requis et doit être une chaîne
  titre: string;
  description: string;
  porteurProjet: string;
  
  encadrant?: string; // Contiendra maintenant le username
  espaceCollaboratif: boolean;
  statutProjet: StatutProjet;  // Statut du projet comme une valeur de l'énumération
  email?: string; // Optionnel en fonction du backend
  telephone?: string; // Optionnel en fonction du backend
  technologies?: string; // Optionnel en fonction du backend
  objectifs?: string; // Optionnel en fonction du backend
  benefices?: string; // Optionnel en fonction du backend
  rejectionMotif?: string; // Optionnel en fonction du backend
}
