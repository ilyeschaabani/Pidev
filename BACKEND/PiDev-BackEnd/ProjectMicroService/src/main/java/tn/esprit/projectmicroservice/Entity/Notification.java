package tn.esprit.projectmicroservice.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.projectmicroservice.Entity.Enumeration.NotificationType;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;



@Document(collection = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    private String id;


    private String userId;


    private String message;

    private NotificationType type;

    private boolean read = false;

    @CreatedDate
    private LocalDateTime createdDate;

    public void setUserId(String userId) {
    }

    public void setMessage(String message) {
    }

    public void setType(NotificationType type) {
    }
}
