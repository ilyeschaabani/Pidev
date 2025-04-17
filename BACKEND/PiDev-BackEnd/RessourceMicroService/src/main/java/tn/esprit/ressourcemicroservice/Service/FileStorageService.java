package tn.esprit.ressourcemicroservice.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeRessource;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    // URL de l'API d'extraction de texte
    private final static String EXTRACT_API_URL="http://127.0.0.1:8000/extract-text/";

    // Méthode pour télécharger un fichier
    public String uploadFile(MultipartFile file, TypeRessource type) throws IOException {

        // Définir le répertoire de téléchargement de base dans le microservice
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/" + type.toString().toLowerCase();
        Path storagePath = Paths.get(uploadDir);

        // S'assurer que le répertoire existe
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        // Générer un nom de fichier unique
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Sauvegarder le fichier physiquement
        Path filePath = storagePath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retourner le nom du fichier unique
        return uniqueFileName;
    }

    // Méthode pour obtenir l'extension d'un fichier
    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    // Méthode pour extraire un résumé d'un document
    public String resumeDocument(String fileName) throws IOException {
        try {
            // Construire le chemin du fichier dans le répertoire ressources/uploads
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/document";
            File file = new File(uploadDir + "/" + fileName);

            // Vérifier si le fichier existe
            if (!file.exists()) {
                throw new IOException("FICHIER INEXISTANT !");
            }

            // Préparer le fichier en tant que Resource (FileSystemResource)
            RestTemplate restTemplate = new RestTemplate();

            // Créer les données du formulaire multipart
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            Resource resource = new FileSystemResource(file);  // Convertir le fichier en ressource
            body.add("file", resource);

            // Définir les en-têtes pour le type de contenu multipart/form-data
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Créer l'entité Http pour envoyer les données du formulaire multipart
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

            // Faire une requête POST vers l'API externe
            ResponseEntity<String> response = restTemplate.exchange(
                    EXTRACT_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Analyser la réponse JSON pour extraire le champ "summary"
            String jsonResponse = response.getBody();
            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                return jsonNode.get("summary").asText(); // Extraire et retourner le résumé
            } else {
                throw new IOException("Réponse vide de l'API externe.");
            }

        } catch (Exception e) {
            throw new IOException("Erreur lors du traitement du fichier : " + e.getMessage());
        }
    }
}
