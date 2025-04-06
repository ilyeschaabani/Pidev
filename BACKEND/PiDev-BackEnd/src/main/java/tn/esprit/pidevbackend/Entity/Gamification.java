package tn.esprit.pidevbackend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import tn.esprit.pidevbackend.Entity.Enumeration.Defi;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

// MongoDB : Remplace @Entity
@Document(collection = "gamifications")
public class Gamification {

    @Id  // MongoDB utilise une clé de type String automatiquement générée
    String idGamification;

    int pts;
    int niveau;
    String badge;
    Defi defi;

    @DBRef // Référence vers l'entité Evaluation
    Evaluation idEvaluation;

    public String getIdGamification() {
        return idGamification;
    }

    public void setIdGamification(String idGamification) {
        this.idGamification = idGamification;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Defi getDefi() {
        return defi;
    }

    public void setDefi(Defi defi) {
        this.defi = defi;
    }

    public Evaluation getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Evaluation idEvaluation) {
        this.idEvaluation = idEvaluation;
    }
}
