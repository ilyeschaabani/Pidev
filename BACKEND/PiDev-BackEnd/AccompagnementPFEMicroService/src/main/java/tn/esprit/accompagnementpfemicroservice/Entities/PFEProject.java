package tn.esprit.accompagnementpfemicroservice.Entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("pfe_projects")
public class PFEProject {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> studentIds;
    private String mentorId;
    private ProjectStage stage;
    private List<DocumentFile> documents;
    private List<Comment> comments = new ArrayList<>();
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    private User mentor; // ou autre nom de classe

    public String getMentorUsername() {
        return mentor != null ? mentor.getUsername() : null;
    }
}
