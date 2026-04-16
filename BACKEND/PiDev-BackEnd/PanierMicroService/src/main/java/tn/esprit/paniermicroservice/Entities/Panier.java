package tn.esprit.paniermicroservice.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.paniermicroservice.dto.FormationDTO;

import java.util.ArrayList;
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
    private String id;
    private Long userId;
    private List<FormationDTO> formations =new ArrayList<>();;
    private double total;

    public Panier(Long userId) {
        this.userId = userId;
        this.formations = new ArrayList<>(); // this line is key!
        this.total = 0.0;
    }

}



