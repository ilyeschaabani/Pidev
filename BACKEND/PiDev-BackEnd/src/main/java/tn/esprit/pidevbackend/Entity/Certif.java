package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certif {
    @Id
    String idCertif;
    String idFormation;
    String id_etudiant;
    @OneToOne (mappedBy = "idCertif")
    Evaluation evaluation;

}
