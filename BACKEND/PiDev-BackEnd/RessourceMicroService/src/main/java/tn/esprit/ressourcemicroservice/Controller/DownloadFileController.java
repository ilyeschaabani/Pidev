package tn.esprit.ressourcemicroservice.Controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadFileController {

    @GetMapping("/api/download/{type}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String type, @PathVariable String fileName) throws IOException {
        System.out.println("I'm in download file ");
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/uploads/";

        Path filePath = Paths.get(uploadDir + type + "/" + fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            // If the client wants to preview, use 'inline'. Otherwise, use 'attachment' for download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");

            // Optionally, set different media types based on file types
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .headers(headers)
                    .body(resource);
        } else {
            throw new IOException("Could not read the file!");
        }
    }
}
