package tn.esprit.projectmicroservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.projectmicroservice.Entity.Enumeration.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    @Id
    private String id;
    private String projectId;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.TO_DO;
    private LocalDate deadline;
    private String assignedTo;
    private List<String> deliverables = new ArrayList<>();
    private Date startDate;
    private Date dueDate;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    public void setCreatedBy(String currentUser) {
    }
}