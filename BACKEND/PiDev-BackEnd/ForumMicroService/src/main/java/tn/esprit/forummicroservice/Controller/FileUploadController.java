package tn.esprit.forummicroservice.Controller;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.core.io.Resource;
 import org.springframework.http.HttpHeaders;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.forummicroservice.Entity.FileType;
import tn.esprit.forummicroservice.Service.FileStorageService;

 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    @Autowired
    private final FileStorageService fileStorageService;

    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("fileType") FileType fileType) {
        String fileName = fileStorageService.saveFile(file,fileType); // Assure-toi que ta méthode s'appelle `saveImage`

        Map<String, String> response = new HashMap<>();
        response.put("fileName", fileName);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{fileType}/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable FileType fileType,
                                                 @PathVariable String fileName) {
        Resource resource = fileStorageService.getFileAsResource(fileName, fileType);

        // Optional: detect content type
        String contentType = "application/octet-stream";
        try {
            Path path = resource.getFile().toPath();
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            // fallback to default
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
