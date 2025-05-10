package tn.esprit.paniermicroservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.paniermicroservice.Entities.CategoryResource;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FormationDTO {
    private String idFormation;
    private String titreFormation;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private CategoryResource categorie;
    private double rating;
    private double prix;
}

