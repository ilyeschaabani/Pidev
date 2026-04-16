package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.Query;
import tn.esprit.projectmicroservice.Entity.Enumeration.TaskStatus;
import tn.esprit.projectmicroservice.Entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{ 'projectId' : ?0, 'assignedTo' : ?1 }")
    List<Task> findByProjectAndUser(String projectId, String email);

    List<Task> findByStatusAndDeadlineBefore(TaskStatus status, LocalDate date);

    List<Task> findByProjectIdAndAssignedTo(String projectId, String email);

    List<Task> findByProjectId(String projectId);
}