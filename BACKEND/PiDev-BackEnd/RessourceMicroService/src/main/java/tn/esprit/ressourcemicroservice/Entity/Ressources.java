package tn.esprit.ressourcemicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.CategoryResource;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeResource;

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
    @Field("idRessource") // Nom du champ dans MongoDB
    String idRessource;

    String titre;
    String description;

    // Enregistre en tant que chaîne pour éviter des erreurs avec MongoDB
    TypeResource type;

    Date date;

    CategoryResource category;

    /* Relation ManyToOne non supportée par MongoDB directement
    @ManyToOne
    User user;
    */
}
