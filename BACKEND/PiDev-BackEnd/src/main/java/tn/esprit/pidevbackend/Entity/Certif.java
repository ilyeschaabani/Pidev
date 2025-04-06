package tn.esprit.pidevbackend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

// MongoDB : Remplace @Entity
@Document(collection = "certifs")
public class Certif {

    @Id  // MongoDB utilise une clé de type String automatiquement générée
    String idCertif;

    public String getIdCertif() {
        return idCertif;
    }

    public void setIdCertif(String idCertif) {
        this.idCertif = idCertif;
    }

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }

    public String getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    String idFormation;
    String idEtudiant; // Changement de nom pour respecter la convention Java

    @DBRef // Référence vers l'entité Evaluation
    Evaluation evaluation;
}
