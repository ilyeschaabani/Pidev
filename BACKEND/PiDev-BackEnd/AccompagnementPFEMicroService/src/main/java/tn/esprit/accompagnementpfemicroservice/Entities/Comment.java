package tn.esprit.accompagnementpfemicroservice.Entities;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String content;
    private String createdBy;
    private Date createdAt;

    // Constructeur utile si createdAt est laissé vide dans la requête
    public Comment(String content, String createdBy) {
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = new Date();
    }
}
