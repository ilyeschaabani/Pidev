package tn.esprit.projectmicroservice.Service;

import org.springframework.stereotype.Service;
import tn.esprit.projectmicroservice.Entity.ChatMessage;
import tn.esprit.projectmicroservice.Repository.ChatMessageRepository;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {
    private final ChatMessageRepository chatRepository;

    public ChatService(ChatMessageRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatMessage saveMessage(ChatMessage message) {
        message.setTimestamp(new Date());
        return chatRepository.save(message);
    }

    public List<ChatMessage> getProjectMessages(String projectId) {
        return chatRepository.findByProjectIdOrderByTimestampAsc(projectId);
    }
}