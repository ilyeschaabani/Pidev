export interface Formation {
    idFormation: string;
    image: string;
    titreFormation: string;
    description: string;
    dateDebut: Date;
    dateFin: Date;
    categorie: CategoryResource; // Define this enum if needed
    rating: number;
  }
  
  // Optional: If using CategoryResource as enum
  export enum CategoryResource {
    DEVELOPMENT = 'DEVELOPMENT',
    DESIGN = 'DESIGN',
    BUSINESS = 'BUSINESS'
  }