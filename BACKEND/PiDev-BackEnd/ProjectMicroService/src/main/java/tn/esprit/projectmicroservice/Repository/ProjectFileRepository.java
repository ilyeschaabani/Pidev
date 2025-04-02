package tn.esprit.projectmicroservice.Repository;

import tn.esprit.projectmicroservice.Entity.ProjectFile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProjectFileRepository extends MongoRepository<ProjectFile, String> {

    List<ProjectFile> findByProjectIdOrderByUploadDateDesc(String projectId, Pageable pageable);

    List<ProjectFile> findByProjectId(String projectId);

    List<ProjectFile> findByFileType(String fileType);

    List<ProjectFile> findByUploadedBy(String uploadedBy);
}