// ChatController.java
package tn.esprit.projectmicroservice.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projectmicroservice.Entity.ChatMessage;
import tn.esprit.projectmicroservice.Repository.ChatMessageRepository;

@RestController
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageRepository chatMessageRepository) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        // Sauvegarder dans la base MongoDB
        chatMessageRepository.save(chatMessage);

        // Envoyer aux abonnés WebSocket
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getProjectId(), chatMessage);
    }
}
