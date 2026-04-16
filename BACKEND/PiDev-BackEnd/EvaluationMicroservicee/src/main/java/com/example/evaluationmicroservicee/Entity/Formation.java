package com.example.evaluationmicroservicee.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

// MongoDB : Remplace @Entity
@Document(collection = "formations")
public class Formation {

    @Id  // MongoDB utilise une clé de type String automatiquement générée
    String idFormation;
    String image;
    String titreFormation;
    String description;
    Date dateDebut;
    Date dateFin;
    String video;
    String categorie;

    // Relations en MongoDB avec @DBRef
    @DBRef
    private List<Evaluation> evaluations;
//
//    @DBRef
//    private Set<User> users;
//
//    @DBRef
//    private Rating rating;
//
//    @DBRef
//    private Panier panier;
}
