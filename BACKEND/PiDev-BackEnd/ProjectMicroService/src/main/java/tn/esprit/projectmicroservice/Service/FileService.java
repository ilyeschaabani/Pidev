package tn.esprit.projectmicroservice.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.projectmicroservice.Entity.ProjectFile;
import tn.esprit.projectmicroservice.Repository.ProjectFileRepository;

import java.io.IOException;
import java.util.Date;

@Service
public class FileService {
    private final ProjectFileRepository fileRepository;

    public FileService(ProjectFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public ProjectFile saveFile(MultipartFile file, String projectId) throws IOException {
        ProjectFile projectFile = new ProjectFile();
        projectFile.setProjectId(projectId);
        projectFile.setFileName(file.getOriginalFilename());
        projectFile.setFileType(file.getContentType());
        projectFile.setContent(file.getBytes());
        projectFile.setUploadDate(new Date());
        return fileRepository.save(projectFile);
    }

    public ProjectFile getFile(String id) {
        return fileRepository.findById(id).orElseThrow();
    }
}