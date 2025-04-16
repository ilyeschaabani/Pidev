package tn.esprit.projectmicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "project_files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile {
    @Id
    private String id;
    private String projectId;
    private String fileName;
    private String fileType;
    private byte[] content;
    private String uploadedBy;
    private Date uploadDate;
}
