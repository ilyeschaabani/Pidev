package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projectmicroservice.Entity.Notification;

public interface NotificationRepository  extends MongoRepository<Notification, String> {

}
