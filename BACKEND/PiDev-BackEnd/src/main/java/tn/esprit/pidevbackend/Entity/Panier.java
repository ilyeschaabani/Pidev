package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Panier {
    @Id
    String idPanier;
    String id_etudiant;
    String idFormation;
    Float total;

    @OneToMany(mappedBy = "panier")
    Set<Formation > formations;

}
