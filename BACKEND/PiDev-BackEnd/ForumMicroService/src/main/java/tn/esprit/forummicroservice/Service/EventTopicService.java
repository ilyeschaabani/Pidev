package tn.esprit.forummicroservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.forummicroservice.Entity.EventTopic;
import tn.esprit.forummicroservice.Repository.EventTopicRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTopicService {

    private final EventTopicRepository repository;

    public List<EventTopic> getAll() {
        return repository.findAll();
    }

    public Optional<EventTopic> getById(String id) {
        return repository.findById(id);
    }

    public EventTopic create(EventTopic eventTopic) throws IllegalArgumentException {
        if (eventTopic.isEvent() && (eventTopic.getLocation() == null || eventTopic.getEventDate() == null)) {
            throw new IllegalArgumentException("EVENT MUST HAVE valid location and date");
        }
        System.out.println(eventTopic.isEvent());
        return repository.save(eventTopic);
    }


    public EventTopic update(String id, EventTopic updatedEventTopic) {
        return repository.findById(id)
                .map(existingEvent -> {

                    if (updatedEventTopic.getTitle() != null && !updatedEventTopic.getTitle().equals(existingEvent.getTitle())) {
                        existingEvent.setTitle(updatedEventTopic.getTitle());
                    }

                    if (updatedEventTopic.getDescription() != null && !updatedEventTopic.getDescription().equals(existingEvent.getDescription())) {
                        existingEvent.setDescription(updatedEventTopic.getDescription());
                    }

                    if (updatedEventTopic.getEventDate() != null && !updatedEventTopic.getEventDate().equals(existingEvent.getEventDate())) {
                        existingEvent.setEventDate(updatedEventTopic.getEventDate());
                    }

                    if (updatedEventTopic.isEvent() != existingEvent.isEvent()) {
                        existingEvent.setEvent(updatedEventTopic.isEvent());
                    }

                    if (updatedEventTopic.getLocation() != null && !updatedEventTopic.getLocation().equals(existingEvent.getLocation())) {
                        existingEvent.setLocation(updatedEventTopic.getLocation());
                    }

                    return repository.save(existingEvent);
                })
                .orElseThrow(() -> new RuntimeException("EventTopic non trouvé avec l'id: " + id));
    }


    public void delete(String id) {
        repository.deleteById(id);
    }
}
