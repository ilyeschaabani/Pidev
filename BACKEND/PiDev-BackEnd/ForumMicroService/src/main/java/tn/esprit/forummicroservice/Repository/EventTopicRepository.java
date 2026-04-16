package tn.esprit.forummicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.forummicroservice.Entity.EventTopic;

import java.util.List;

public interface EventTopicRepository extends MongoRepository<EventTopic, String> {
    List<EventTopic> findByIsEvent(boolean isEvent);
}