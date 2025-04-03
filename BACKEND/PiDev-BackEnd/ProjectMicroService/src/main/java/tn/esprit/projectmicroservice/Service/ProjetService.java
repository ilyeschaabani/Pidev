package tn.esprit.projectmicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.projectmicroservice.Entity.Enumeration.NotificationType;
import tn.esprit.projectmicroservice.Entity.Enumeration.TaskStatus;
import tn.esprit.projectmicroservice.Entity.Notification;
import tn.esprit.projectmicroservice.Entity.Projet;
import tn.esprit.projectmicroservice.Entity.Task;
import tn.esprit.projectmicroservice.Entity.User;
import tn.esprit.projectmicroservice.Repository.*;
import tn.esprit.projectmicroservice.Entity.Enumeration.StatutProjet;

import java.util.List;

@Service
public class ProjetService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    private final ProjetRepository projetRepository;
    private final UserRepository userRepository;


    @Autowired
    public ProjetService(
            ProjetRepository projetRepository,
            UserRepository userRepository,
            TaskRepository taskRepository,
            CommentRepository commentRepository,
            NotificationRepository notificationRepository,
            SimpMessagingTemplate messagingTemplate
    ){
        this.projetRepository = projetRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public Projet addProjet(Projet projet) {
        // Ajoute directement le projet sans gestion des fichiers
        return projetRepository.save(projet);
    }
    public List<Task> getTasksByProject(String projectId) {
        return taskRepository.findByProjectId(projectId);
    }


    public Projet updateProjet(String id, Projet newProjet) {
        return projetRepository.findById(id)
                .map(existingProjet -> {
                    existingProjet.setTitre(newProjet.getTitre());
                    existingProjet.setDescription(newProjet.getDescription());
                    existingProjet.setPorteurProjet(newProjet.getPorteurProjet());
                    existingProjet.setEncadrant(newProjet.getEncadrant());
                    existingProjet.setEspaceCollaboratif(newProjet.getEspaceCollaboratif());
                    existingProjet.setStatutProjet(newProjet.getStatutProjet());
                    existingProjet.setEmail(newProjet.getEmail());
                    existingProjet.setTelephone(newProjet.getTelephone());
                    existingProjet.setTechnologies(newProjet.getTechnologies());
                    existingProjet.setObjectifs(newProjet.getObjectifs());
                    existingProjet.setBenefices(newProjet.getBenefices());
                    existingProjet.setRejectionMotif(newProjet.getRejectionMotif()); // Ajout du motif de rejet
                    return projetRepository.save(existingProjet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public String deleteProjet(String id) {
        return projetRepository.findById(id)
                .map(projet -> {
                    projetRepository.deleteById(id);
                    return "Projet supprimé avec succès";
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public List<Projet> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();

        projets.forEach(projet -> {
            if(projet.getEncadrant() != null && !projet.getEncadrant().isEmpty()) {
                userRepository.findById(projet.getEncadrant())
                        .ifPresent(user -> projet.setEncadrant(user.getUsername()));
            }
        });

        return projets;
    }

    public Projet getProjetById(String id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public Projet validateOrRejectProjet(String id, boolean isValid, String rejectionMotif) {
        return projetRepository.findById(id)
                .map(projet -> {
                    if (isValid) {
                        projet.setStatutProjet(StatutProjet.EN_COURS);
                        projet.setRejectionMotif(null); // Effacer le motif de rejet si accepté
                    } else {
                        projet.setStatutProjet(StatutProjet.EN_ATTENTE);
                        projet.setRejectionMotif(rejectionMotif); // Stocker le motif de rejet
                    }
                    return projetRepository.save(projet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public Projet assignEncadrant(String projetId, String encadrantId) {
        return projetRepository.findById(projetId)
                .map(projet -> {
                    User encadrant = userRepository.findById(encadrantId)
                            .orElseThrow(() -> new RuntimeException("Encadrant non trouvé"));

                    if(!"ENCADRANT".equals(encadrant.getRole())) {
                        throw new RuntimeException("L'utilisateur n'est pas un encadrant");
                    }

                    projet.setEncadrant(encadrant.getUsername()); // Stocker le username au lieu de l'ID
                    return projetRepository.save(projet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }
    public List<Task> getUserTasks(String projectId, String email) {
        return taskRepository.findByProjectIdAndAssignedTo(projectId, email);
    }
    @Transactional
    public Task createTask(String projectId, Task task, String currentUser) {
        task.setCreatedBy(currentUser);
        Task savedTask = taskRepository.save(task);

        sendNotification(savedTask.getAssignedTo(),
                "Nouvelle tâche assignée: " + task.getTitle(),
                NotificationType.TASK_ASSIGNMENT);

        return savedTask;
    }

    private void sendNotification(String userId, String message, NotificationType type) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(type);
        notificationRepository.save(notification);

        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/notifications",
                notification
        );
    }


    // Update Task Status
    public Task updateTaskStatus(String taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    // Update Task Details
    public Task updateTask(String taskId, Task updatedTask) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(updatedTask.getTitle()); // Use 'title' instead of 'name'
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDeadline(updatedTask.getDeadline());
        return taskRepository.save(existingTask);
    }


    // Delete Task
    public void deleteTask(String taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(taskId);
    }
}

