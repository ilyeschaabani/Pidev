package tn.esprit.pidevbackend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.pidevbackend.Entity.Enumeration.Role;

@Document(collection = "users") // Spécifie la collection MongoDB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    String idUser;  // L'ID sera géré automatiquement par MongoDB si non spécifié

    String nom;
    String prenom;
    String email;
    String password;
    Role role;
    String photo;
    String telephone;
    String adresse;
}
