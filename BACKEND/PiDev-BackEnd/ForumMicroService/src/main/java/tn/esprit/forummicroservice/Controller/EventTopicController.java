package tn.esprit.forummicroservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.forummicroservice.Entity.EventTopic;
import tn.esprit.forummicroservice.Service.EventTopicService;

import java.util.List;

@RestController
@RequestMapping("/api/events-topics")
@RequiredArgsConstructor
public class EventTopicController {

    private final EventTopicService service;

    @GetMapping
    public ResponseEntity<List<EventTopic>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventTopic> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventTopic> create(@RequestBody EventTopic eventTopic) {
        return ResponseEntity.ok(service.create(eventTopic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventTopic> update(@PathVariable String id, @RequestBody EventTopic eventTopic) {
        return ResponseEntity.ok(service.update(id, eventTopic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
