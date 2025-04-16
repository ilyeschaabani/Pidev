package tn.esprit.accompagnementpfemicroservice.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
                .build();
    }

    public String generatePfeTopics(String studentProfile) {
        String prompt = String.format("Génère 3 sujets de PFE adaptés aux compétences et intérêts suivants : %s. Pour chaque sujet, fournis un titre, une description et une justification.", studentProfile);

        // Création correcte du body
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(textPart));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(content));

        try {
            return webClient.post()
                    .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(requestBody), Map.class)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        // Débogage optionnel
                        System.out.println("Réponse brute Gemini: " + response);

                        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                        if (candidates != null && !candidates.isEmpty()) {
                            Map<String, Object> contentMap = (Map<String, Object>) candidates.get(0).get("content");
                            if (contentMap != null) {
                                List<Map<String, Object>> parts = (List<Map<String, Object>>) contentMap.get("parts");
                                if (parts != null && !parts.isEmpty()) {
                                    Object textObj = parts.get(0).get("text");
                                    return textObj != null ? textObj.toString() : "Aucun texte trouvé dans la réponse.";
                                }
                            }
                        }
                        return "Aucun sujet généré.";
                    })
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération des sujets : " + e.getMessage();
        }
    }
}
