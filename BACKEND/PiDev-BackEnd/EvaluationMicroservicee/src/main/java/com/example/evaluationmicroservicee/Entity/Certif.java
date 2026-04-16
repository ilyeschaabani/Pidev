package com.example.evaluationmicroservicee.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

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
    Date dateObtention;
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

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }
}
