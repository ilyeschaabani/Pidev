package tn.esprit.projectmicroservice.Controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tn.esprit.projectmicroservice.Entity.ChatMessage;
import tn.esprit.projectmicroservice.Service.ChatService;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.send/{projectId}")
    @SendTo("/topic/messages/{projectId}")
    public ChatMessage sendMessage(@DestinationVariable String projectId, ChatMessage message) {
        message.setProjectId(projectId);
        return chatService.saveMessage(message);
    }
}