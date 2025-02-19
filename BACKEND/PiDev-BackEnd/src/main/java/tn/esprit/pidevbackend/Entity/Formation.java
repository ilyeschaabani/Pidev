package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Formations")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String idFormation;
    String image;
    String titreFormation;
    String description;
    Date dateDebut;
    Date dateFin;
    String categorie;

    public String getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(String idFormation) {
        this.idFormation = idFormation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitreFormation() {
        return titreFormation;
    }

    public void setTitreFormation(String titreFormation) {
        this.titreFormation = titreFormation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }


    /*
    @OneToOne
    Evaluation evaluation;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<User> users;
    @OneToOne (mappedBy = "formation")
    Rating rating;
    @ManyToOne
    Panier panier;
*/
}
