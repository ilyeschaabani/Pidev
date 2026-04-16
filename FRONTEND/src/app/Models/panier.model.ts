export interface FormationDTO {
    idFormation: string;
    titreFormation: string;
    description: string;
    dateDebut: Date;
    dateFin: Date;
    categorie: CategoryResource;
    rating: number;
    prix: number;
  }
  
  export interface Panier {
    id?: string;
    userId: number;
    formations: FormationDTO[];
    total: number;
  }
  
  export enum CategoryResource {
    DATABASE, PROGRAMMING, DESIGN, NETWORKING, SECURITY, OTHER
  }
  