package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccompagnementPFE {
    @Id
    String idAccompagnement;
    String etudiant;
    String encadrant;
    String sujet;
    Float Avancement;

    @ManyToMany
    Set<User> participantsPFE;

}
