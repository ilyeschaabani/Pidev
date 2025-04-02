package tn.esprit.projectmicroservice.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.projectmicroservice.Entity.ProjectFile;
import tn.esprit.projectmicroservice.Repository.ProjectFileRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    private final ProjectFileRepository fileRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FileService(ProjectFileRepository fileRepository, MongoTemplate mongoTemplate) {
        this.fileRepository = fileRepository;
        this.mongoTemplate = mongoTemplate;
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

    public List<ProjectFile> findByProjectId(String projectId) {
        // Solution 1: Si projectId est stocké comme String dans MongoDB
        return mongoTemplate.find(
                Query.query(Criteria.where("projectId").is(projectId)),
                ProjectFile.class
        );

        // Solution 2: Si projectId est stocké comme ObjectId dans MongoDB
        // return mongoTemplate.find(
        //     Query.query(Criteria.where("projectId").is(new ObjectId(projectId))),
        //     ProjectFile.class
        // );
    }
}