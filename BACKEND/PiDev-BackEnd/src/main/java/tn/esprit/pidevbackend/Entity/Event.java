package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Event {
    @Id
    String idEvent;
    String titre;
    String description;
    String Localisation;
    Date date;

    @ManyToMany (cascade = CascadeType.ALL)
    Set<User> participants;
}
