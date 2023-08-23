package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.Agent;
import projet.pfe.tms.models.Task;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Override
    Optional<Task> findById(Long id);
    Optional<Task> findByTaskDetails(String taskDetails);

    int countByStatus(TaskStatus taskStatus);

    Task findByTaskId(Long taskId);



}

