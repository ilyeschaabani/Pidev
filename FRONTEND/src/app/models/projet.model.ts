export enum StatutProjet {
    EN_ATTEND = 'En attente',
    EN_COURS = 'En cours',
    TERMINE = 'Terminé',
  }
  
export interface Projet {
    idProjet: string;
    titre: string;
    description: string;
    porteurProjet: string;
    encadrant: string;
    espaceCollaboratif: boolean;
    statutProjet: StatutProjet;  // ou un type d'énumération si nécessaire
  }
  