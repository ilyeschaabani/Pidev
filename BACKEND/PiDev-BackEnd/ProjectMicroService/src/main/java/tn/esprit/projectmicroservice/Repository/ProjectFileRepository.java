package tn.esprit.projectmicroservice.Repository;


import tn.esprit.projectmicroservice.Entity.ProjectFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProjectFileRepository extends MongoRepository<ProjectFile, String> {

    /**
     * Trouve tous les fichiers d'un projet
     * @param projectId ID du projet
     * @return Liste des fichiers du projet
     */
    List<ProjectFile> findByProjectId(String projectId);

    /**
     * Trouve les fichiers par type MIME
     * @param fileType Type de fichier (ex: "image/png")
     * @return Liste des fichiers correspondants
     */
    List<ProjectFile> findByFileType(String fileType);

    /**
     * Trouve les fichiers uploadés par un utilisateur
     * @param uploadedBy ID de l'utilisateur
     * @return Liste des fichiers de l'utilisateur
     */
    List<ProjectFile> findByUploadedBy(String uploadedBy);
}