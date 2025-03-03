package tn.esprit.forummicroservice.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.forummicroservice.Entity.Comment;
import tn.esprit.forummicroservice.Entity.EventTopic;
import tn.esprit.forummicroservice.Repository.CommentRepository;
import tn.esprit.forummicroservice.Repository.EventTopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventTopicRepository eventTopicRepository;  // Repository to update EventTopic

    // Créer un commentaire et mettre à jour le EventTopic
    public Comment create(Comment comment) {
        // Sauvegarder le commentaire
        Comment createdComment = commentRepository.save(comment);

        // Récupérer l'EventTopic associé en utilisant postId ou eventTopicId
        EventTopic eventTopic = eventTopicRepository.findById(comment.getEventTopicId())
                .orElseThrow(() -> new RuntimeException("EventTopic non trouvé"));

        // Ajouter l'ID du commentaire à la liste des commentaires
        if(eventTopic.getCommentIds()==null){
            eventTopic.setCommentIds(new ArrayList<>());

        }
        eventTopic.getCommentIds().add(createdComment.getId());

        // Sauvegarder l'EventTopic mis à jour
        eventTopicRepository.save(eventTopic);

        return createdComment;
    }

    // Mise à jour d'un commentaire
    public Comment update(String commentId, Comment updatedComment) {
        return commentRepository.findById(commentId)
                .map(existingComment -> {
                    existingComment.setContent(updatedComment.getContent());
                    existingComment.setUpdatedAt(updatedComment.getUpdatedAt());
                    return commentRepository.save(existingComment);
                })
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec l'id: " + commentId));
    }

    // Supprimer un commentaire et mettre à jour l'EventTopic
    public void delete(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Commentaire non trouvé avec l'id: " + commentId));

        // Supprimer l'ID du commentaire de la liste des commentaires dans EventTopic
        EventTopic eventTopic = eventTopicRepository.findById(comment.getEventTopicId())
                .orElseThrow(() -> new RuntimeException("EventTopic non trouvé"));

        // Enlever le commentaire de la liste
        eventTopic.getCommentIds().remove(commentId);

        // Sauvegarder l'EventTopic mis à jour
        eventTopicRepository.save(eventTopic);

        // Supprimer le commentaire
        commentRepository.delete(comment);
    }
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Lire un commentaire par son ID
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    // Autres méthodes de service, si nécessaire
}
