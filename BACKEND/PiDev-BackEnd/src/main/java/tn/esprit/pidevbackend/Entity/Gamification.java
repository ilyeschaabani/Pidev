package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.Defi;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Gamification {
    @Id
    String idGamification;
    int pts;
    int niveau;
    String badge;
    Defi defi;

    @OneToOne(mappedBy = "idGamification")
    Evaluation idEvaluation;
}
