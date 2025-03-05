package tn.esprit.ressourcemicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.CategoryRessource;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeRessource;

import java.util.Date;

@Document(collection = "ressources") // Spécifie la collection MongoDB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ressources {

    @Id
    String idRessource;

    String titre;
    String description;

    // Enregistre en tant que chaîne pour éviter des erreurs avec MongoDB
    TypeRessource type;

    Date date;

    CategoryRessource category;
    String fileName;

}
