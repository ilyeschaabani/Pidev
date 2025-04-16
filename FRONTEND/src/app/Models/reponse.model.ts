export interface Reponse {
  id?: string;
  texte: string;       // Texte de la réponse
  correcte: boolean;   // Si la réponse est correcte
  explanation?: string;
}
