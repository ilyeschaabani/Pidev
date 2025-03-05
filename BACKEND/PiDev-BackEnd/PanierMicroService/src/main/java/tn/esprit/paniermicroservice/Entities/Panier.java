package tn.esprit.paniermicroservice.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Paniers")
public class Panier {

    @Id

    private Long idPanier;

    String idEtudiant;


//    @ElementCollection
//    List<Long> formationIds;

    private double total; // Total price of formations in the cart
}

