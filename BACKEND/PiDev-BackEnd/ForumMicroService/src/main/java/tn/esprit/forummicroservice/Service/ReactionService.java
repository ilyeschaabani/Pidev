package tn.esprit.forummicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.forummicroservice.Entity.Comment;
import tn.esprit.forummicroservice.Entity.EmojiType;
import tn.esprit.forummicroservice.Entity.EventTopic;
import tn.esprit.forummicroservice.Entity.Reaction;
import tn.esprit.forummicroservice.Repository.CommentRepository;
import tn.esprit.forummicroservice.Repository.EventTopicRepository;
import tn.esprit.forummicroservice.Repository.ReactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventTopicRepository eventTopicRepository;

    // Add a new reaction (for comment or event topic)
    public Reaction addOrUpdateReaction(String entityName, String entityId, String userId, EmojiType emoji) {
        if (entityName == null || entityId == null || userId == null || emoji == null) {
            throw new IllegalArgumentException("All fields must be provided.");
        }

        // Check if the user already has a reaction for the entity
        List<Reaction> existingReactions = reactionRepository.findByEntityNameAndEntityIdAndUserId(entityName, entityId, userId);
            if(!existingReactions.isEmpty()){
                System.out.println(existingReactions);
                Reaction existingReaction=existingReactions.get(0);
                if (existingReaction != null) {
                    // If the reaction exists, update it
                    existingReaction.setEmoji(emoji);
                    existingReaction.setUpdatedAt(LocalDateTime.now());
                    reactionRepository.save(existingReaction);
                    if (existingReaction.getEntityName().equals("eventTopic")) {
                        eventTopicRepository.findById(entityId).ifPresent(eventTopic -> {
                            // Find the old reaction by ID
                            Reaction oldReaction = eventTopic.getReactions().stream()
                                    .filter(r -> r.getId().equals(existingReaction.getId())) // Use equals() for comparing IDs
                                    .findFirst()
                                    .orElse(null); // If no matching reaction, return null

                            if (oldReaction != null) {
                                // Update the old reaction (replace it with the new one)
                                eventTopic.getReactions().remove(oldReaction); // Remove the old reaction
                                eventTopic.getReactions().add(existingReaction); // Add the updated reaction
                            } else {
                                // If no old reaction is found, add the new reaction to the list
                                eventTopic.getReactions().add(existingReaction);
                            }

                            // Save the updated event topic
                            eventTopicRepository.save(eventTopic);
                        });
                    }else {
                        commentRepository.findById(entityId).ifPresent(comment -> {
                            Reaction oldReaction = comment.getReactions().stream()
                                    .filter(r -> r.getId().equals(existingReaction.getId())) // Use equals() for comparing IDs
                                    .findFirst()
                                    .orElse(null); // If no matching reaction, return null

                            if (oldReaction != null) {
                                // Update the old reaction (replace it with the new one)
                                comment.getReactions().remove(oldReaction); // Remove the old reaction
                                comment.getReactions().add(existingReaction); // Add the updated reaction
                            } else {
                                // If no old reaction is found, add the new reaction to the list
                                comment.getReactions().add(existingReaction);
                            }

                            commentRepository.save(comment);
                        });

                    }

                }                    return existingReaction;

            }
         else {
            // If the reaction doesn't exist, create a new one
            Reaction newReaction = new Reaction();
            newReaction.setEntityName(entityName);
            newReaction.setEntityId(entityId);
            newReaction.setUserId(userId);
            newReaction.setEmoji(emoji);
            newReaction.setCreatedAt(LocalDateTime.now());

            reactionRepository.save(newReaction);

            // Update reactions in the comment or event topic entity
            if ("comment".equals(entityName)) {
                commentRepository.findById(entityId).ifPresent(comment -> {
                    comment.getReactions().add(newReaction);
                    commentRepository.save(comment);
                });
            } else if ("eventTopic".equals(entityName)) {
                System.out.println("Im here in event topic find ");
                eventTopicRepository.findById(entityId).ifPresent(eventTopic -> {
                    eventTopic.getReactions().add(newReaction);
                    eventTopicRepository.save(eventTopic);
                });
            } else {
                throw new IllegalArgumentException("Invalid entityName: " + entityName);
            }

            return newReaction;
        }
    }
    // Remove a reaction from a comment or event topic
    public void removeReaction(String entityName, String entityId, String userId, EmojiType emoji) {
        if (entityName == null || entityId == null || userId == null || emoji == null) {
            throw new IllegalArgumentException("All fields must be provided.");
        }

        // Find the reaction to remove
        Reaction reactionToRemove = reactionRepository.findByEntityNameAndEntityIdAndUserIdAndEmoji(entityName, entityId, userId, emoji);
        if (reactionToRemove != null) {
            reactionRepository.delete(reactionToRemove);
        } else {
            throw new RuntimeException("Reaction not found.");
        }

        // Update the comment or event topic entity
        if ("comment".equals(entityName)) {
            Optional<Comment> comment = commentRepository.findById(entityId);
            if (comment.isPresent()) {
                // Remove the reaction from the comment's reactions list
                comment.get().getReactions().removeIf(r -> r.getUserId().equals(userId) && r.getEmoji().equals(emoji));
                commentRepository.save(comment.get());
            } else {
                throw new RuntimeException("Comment not found with ID: " + entityId);
            }
        } else if ("eventTopic".equals(entityName)) {
            Optional<EventTopic> eventTopic = eventTopicRepository.findById(entityId);
            if (eventTopic.isPresent()) {
                // Remove the reaction from the event topic's reactions list
                eventTopic.get().getReactions().removeIf(r -> r.getUserId().equals(userId) && r.getEmoji().equals(emoji));
                eventTopicRepository.save(eventTopic.get());
            } else {
                throw new RuntimeException("EventTopic not found with ID: " + entityId);
            }
        } else {
            throw new IllegalArgumentException("Invalid entityName: " + entityName);
        }
    }

    // Get all reactions for a comment or event topic
    public List<Reaction> getReactions(String entityName, String entityId) {
        if (entityName == null || entityId == null) {
            throw new IllegalArgumentException("EntityName and EntityId must be provided.");
        }

        return reactionRepository.findByEntityNameAndEntityId(entityName, entityId);
    }
}
