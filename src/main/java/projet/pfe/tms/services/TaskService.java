package projet.pfe.tms.services;
import projet.pfe.tms.dto.TaskDTO;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.Task;
import java.util.List;

public interface TaskService {

    Task addNewTask(TaskDTO taskDto);
    Task updateTask(Long id, TaskDTO taskDTO);
    Task updateData(Task task, TaskDTO taskDto);
    void deleteTask(Long id);
    List<TaskDTO> listTasksDTO();
    List<Task> listTasks();
    Task loadTaskByTaskDetails(String taskDetails);
    Task loadTaskById(Long id);
    TaskDTO loadTaskByTaskId(Long id);

//    List<Task> loadTasksByAgentId(Long taskId);
//    List<Task> loadTasksByClientId(Long clientId);

    Task affectAgentToTask(Long idTask, Long idAgent);
    Task affectClientToTask(Long idTask, Long idClient);

//    Task deleteAgentOfTask(Long id);
//    Task deleteClientOfTask(Long id);

    int countTotalTasks();
    int countScheduledTasks();

    int countCanceledTasks();
    int countDoneTasks();
    Task   updateTaskStatus(Long TaskId, TaskStatus status);

}
