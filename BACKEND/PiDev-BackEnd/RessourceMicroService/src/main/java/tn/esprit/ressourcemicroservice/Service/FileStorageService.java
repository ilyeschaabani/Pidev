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

    private final static String EXTRACT_API_URL="http://127.0.0.1:8000/extract-text/";
    public String uploadFile(MultipartFile file, TypeRessource type) throws IOException {
        // Define the base upload directory inside the microservice
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/" + type.toString().toLowerCase();
        Path storagePath = Paths.get(uploadDir);

        // Ensure directory exists
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        // Generate a unique filename
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // Save the file physically
        Path filePath = storagePath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


        return uniqueFileName;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String resumeDocument(String fileName) throws IOException {
        try {
            // Construct the file path in the resources/uploads directory
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/document";
            File file = new File(uploadDir + "/" + fileName);

            if (!file.exists()) {
                throw new IOException("FILE NOT FOUND!");
            }

            // Prepare the file as a Resource (FileSystemResource)
            RestTemplate restTemplate = new RestTemplate();

            // Create the multipart form data
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            Resource resource = new FileSystemResource(file);  // Convert the file to a resource
            body.add("file", resource);

            // Set up the headers for multipart/form-data
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Create the HttpEntity to send the multipart form data
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

            // Make the POST request to the external API
            ResponseEntity<String> response = restTemplate.exchange(
                    EXTRACT_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Parse the JSON response to extract the summary field
            String jsonResponse = response.getBody();
            if (jsonResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                return jsonNode.get("summary").asText(); // Extract and return the "summary"
            } else {
                throw new IOException("Empty response from external API.");
            }


        } catch (Exception e) {
            throw new IOException("Error processing file: " + e.getMessage());
        }
    }
}
