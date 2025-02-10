package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.EtatEvaluation;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evaluation {
    @Id
    String idEvaluation;
    //String Etudiant;
    int note;
    EtatEvaluation etat;


    @OneToOne(mappedBy = "evaluation")
    Formation idFormation;
    @OneToOne
    Certif idCertif;
    @OneToOne
    Gamification idGamification;

}
