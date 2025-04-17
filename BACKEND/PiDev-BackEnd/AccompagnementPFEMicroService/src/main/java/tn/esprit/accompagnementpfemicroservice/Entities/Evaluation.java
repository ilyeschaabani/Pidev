package tn.esprit.accompagnementpfemicroservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    private int progressScore;
    private int finalScore;
    private String feedback;
}