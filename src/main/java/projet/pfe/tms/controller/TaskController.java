package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.AgentDTO;
import projet.pfe.tms.dto.TaskDTO;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.Agent;
import projet.pfe.tms.models.Task;
import projet.pfe.tms.services.AgentService;
import projet.pfe.tms.services.TaskService;

import java.util.List;

@CrossOrigin("**")
@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks(){
        return this.taskService.listTasks();
    }
    @GetMapping("/dto")
    public List<TaskDTO> getAllTasksDTO(){
        return this.taskService.listTasksDTO();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id){
        return this.taskService.loadTaskByTaskId(id);
    }

    @PostMapping("/add-task")
    public ResponseEntity<String> addNewTask(@RequestBody TaskDTO taskDto){


        if(this.taskService.addNewTask(taskDto) != null)
            return ResponseEntity.ok("La tâche a été ajouté avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout de la tâche");
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDto){

        if(this.taskService.updateTask(id, taskDto) != null )
            return ResponseEntity.ok("La tâche a été modifié avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification de la tâche");
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id){
        this.taskService.deleteTask(id);
        return ResponseEntity.ok("La tâche a été supprimé avec succès");
    }

//    @PutMapping("/update-agent/{id}/affect-commercial/{staffId}")
//    public ResponseEntity<String> affectCommercialToAgent(@PathVariable Long id, @PathVariable Long staffId){
//        if(this.agentService.affectCommercialToAgent(id, staffId) != null )
//            return ResponseEntity.ok("Le commercial a été affecté à l'agent avec succès");
//
//        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'affectation du commercial à l'agent");
//    }

//    @DeleteMapping("/update-agent/{id}/delete-commercial")
//    public ResponseEntity<String> deleteCommercialOfAgent(@PathVariable Long id){
//        if(this.agentService.deleteCommercialOfAgent(id) != null)
//            return ResponseEntity.ok("Le commercial a été supprimé avec succès");
//        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la suppression du commercial");
//    }

    @GetMapping("/Total-Tasks")
    public int countTotalTasks() {
        return this.taskService.countTotalTasks();
    }

    @GetMapping("/Total-Scheduled-Tasks")
    public int countScheduledTasks() {
        return this.taskService.countScheduledTasks();
    }

    @GetMapping("/Total-Done-Tasks")
    public int countDoneTasks() {
        return this.taskService.countDoneTasks();
    }

    @GetMapping("/Total-Canceled-Tasks")
    public int countCanceledTasks() {
        return this.taskService.countCanceledTasks();
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        Task task = taskService.updateTaskStatus(taskId, status);
        if (task != null) {
            return ResponseEntity.ok("Le statut de tâche a été mis à jour avec succès");
        } else {
            return ResponseEntity.badRequest().body("Echec de la mise à jour du statut de tâche");
        }
    }
}
