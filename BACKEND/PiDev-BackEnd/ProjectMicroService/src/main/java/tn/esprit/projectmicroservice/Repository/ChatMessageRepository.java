package tn.esprit.projectmicroservice.Repository;


import tn.esprit.projectmicroservice.Entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    /**
     * Trouve tous les messages d'un projet triés par date croissante
     * @param projectId ID du projet
     * @return Liste des messages du projet
     */
    List<ChatMessage> findByProjectIdOrderByTimestampAsc(String projectId);

    /**
     * Trouve les derniers messages d'un projet (pour pagination)
     * @param projectId ID du projet
     * @param pageable Configuration de pagination
     * @return Liste paginée des messages
     */
    List<ChatMessage> findByProjectIdOrderByTimestampDesc(String projectId, Pageable pageable);
}