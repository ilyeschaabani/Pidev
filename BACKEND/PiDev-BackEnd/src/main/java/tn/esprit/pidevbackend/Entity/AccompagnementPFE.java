package tn.esprit.pidevbackend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "accompagnementsPFE") // Spécifie la collection MongoDB
@Getter
@Setter
@AllArgsConstructor

@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccompagnementPFE {

    @Id
    String idAccompagnement; // MongoDB génère un ObjectId si laissé vide

    String etudiant;
    String encadrant;
    String sujet;
    Float avancement;

    // Stocke uniquement les IDs des utilisateurs (relation ManyToMany en SQL)
    List<String> participantsPFE;
    public AccompagnementPFE(){}
    public AccompagnementPFE(String etudiant, String encadrant, String sujet, Float avancement, List<String> participantsPFE) {
        this.etudiant = etudiant;
        this.encadrant = encadrant;
        this.sujet = sujet;
        this.avancement = avancement;
        this.participantsPFE = participantsPFE;
    }

    public String getIdAccompagnement() {
        return idAccompagnement;
    }

    public void setIdAccompagnement(String idAccompagnement) {
        this.idAccompagnement = idAccompagnement;
    }

    public String getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(String etudiant) {
        this.etudiant = etudiant;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Float getAvancement() {
        return avancement;
    }

    public void setAvancement(Float avancement) {
        this.avancement = avancement;
    }

    public List<String> getParticipantsPFE() {
        return participantsPFE;
    }

    public void setParticipantsPFE(List<String> participantsPFE) {
        this.participantsPFE = participantsPFE;
    }
}
