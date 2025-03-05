package tn.esprit.microservice.commondtos;

import lombok.Data;

import java.util.Date;


@Data
public class ForamtionDTO {
    String idFormation;
    String image;
    String titreFormation;
    String description;
    Date dateDebut;
    Date dateFin;
    CategoryResource categorie;
    double prix;
    double rating;
}
