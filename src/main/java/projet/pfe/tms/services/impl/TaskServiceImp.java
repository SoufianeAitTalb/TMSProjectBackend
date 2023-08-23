package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.AgentDTO;
import projet.pfe.tms.dto.TaskDTO;
import projet.pfe.tms.enums.TaskStatus;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.AgentRepo;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.repositories.TaskRepo;
import projet.pfe.tms.services.TaskService;
import projet.pfe.tms.services.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService
{

    private final TaskRepo taskRepo;
    private final AgentService agentService;
    private final ClientService clientService;
    private final CountryService countryService;
    private final CurrencyService currencyService;
    private final StaffService staffService;
    private final FolderRepo folderRepo;

    @Autowired
    public TaskServiceImp(TaskRepo taskRepo,
                          AgentService agentService,
                          ClientService clientService,
                          CountryService countryService,
                          CurrencyService currencyService,
                          StaffService staffService, FolderRepo folderRepo){
        this.taskRepo = taskRepo;
        this.agentService=agentService;
        this.clientService=clientService;
        this.countryService = countryService;
        this.currencyService = currencyService;
        this.staffService = staffService;
        this.folderRepo = folderRepo;
    }

    @Transactional
    @Override
    public Task addNewTask(TaskDTO taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setTaskDetails(taskDto.getTaskDetails());
        task.setStatus(TaskStatus.SCHEDULED);
        task.setDueDate(taskDto.getDueDate());
        task.setPlanNextAction(taskDto.getPlanNextAction());
        task.setPriority(taskDto.getPriority());
        task.setRepeatEvery(taskDto.getRepeatEvery());


        // set task's country
        if(taskDto.getAgentId() != null) {
            Agent taskAgent = this.agentService.loadAgentById(taskDto.getAgentId());
            task.setAgent(taskAgent);
        }
        if(taskDto.getClientId() != null) {
            Client taskClient = this.clientService.loadClientById(taskDto.getClientId());
            task.setClient(taskClient);
        }

        return this.taskRepo.save(task);
    }

    @Transactional
    @Override
    public Task updateTask(Long id, TaskDTO taskDto) {
        Task task = this.taskRepo.findById(id).orElse(null);
        if(task != null){
            task = this.updateData(task, taskDto);

            if(taskDto.getAgentId() != null) {
                Agent taskAgent = this.agentService.loadAgentById(taskDto.getAgentId());
                task.setAgent(taskAgent);
            }
            if(taskDto.getClientId()!=null){
            Client taskClient = this.clientService.loadClientById(taskDto.getClientId());
            task.setClient(taskClient);}



            return this.taskRepo.save(task);
        }
        return null;
    }

    @Override
    public Task updateData(Task task, TaskDTO taskDto) {
        task.setName(taskDto.getName());
        task.setTaskDetails(taskDto.getTaskDetails());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        task.setPlanNextAction(taskDto.getPlanNextAction());
        task.setPriority(taskDto.getPriority());
        task.setRepeatEvery(taskDto.getRepeatEvery());

        return task;
    }

    @Override
    public void deleteTask(Long id) {

//        taskRepo.deleteById(id);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));
        taskRepo.delete(task);
    }

    @Override
    public List<Task> listTasks() {
        return this.taskRepo.findAll();
    }

    @Override
    public List<TaskDTO> listTasksDTO() {
        List<Task> tasks = this.taskRepo.findAll();
        List<TaskDTO> tasksDTO = tasks.stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
        return tasksDTO;
    }

    public TaskDTO convertToTaskDTO(Task task) {
         if(task == null){
            return null;
        }
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setTaskDetails(task.getTaskDetails());
        taskDto.setStatus(task.getStatus());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setPlanNextAction(task.getPlanNextAction());
        taskDto.setPriority(task.getPriority());
        taskDto.setRepeatEvery(task.getRepeatEvery());

        if(task.getAgent() != null)
            taskDto.setAgentId(task.getAgent().getAgentId());
        if(task.getClient() != null)
            taskDto.setClientId(task.getClient().getClientId());

        return taskDto;
    }

    @Override
    public Task loadTaskById(Long id) {
        return this.taskRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public TaskDTO loadTaskByTaskId(Long id) {
        Task task = this.taskRepo.findById(id).orElse(null);
        if(task == null){
            return null;
        }
        TaskDTO taskDto = new TaskDTO();
        taskDto.setTaskId(task.getTaskId());
        taskDto.setName(task.getName());
        taskDto.setTaskDetails(task.getTaskDetails());
        taskDto.setStatus(task.getStatus());
        taskDto.setDueDate(task.getDueDate());
        taskDto.setPlanNextAction(task.getPlanNextAction());
        taskDto.setPriority(task.getPriority());
        taskDto.setRepeatEvery(task.getRepeatEvery());

        if(task.getAgent() != null)
            taskDto.setAgentId(task.getAgent().getAgentId());
        if(task.getClient() != null)
            taskDto.setClientId(task.getClient().getClientId());

        return taskDto;
    }


    @Override
    public Task loadTaskByTaskDetails(String taskDetails) {
        return this.taskRepo
                .findByTaskDetails(taskDetails)
                .orElse(null);
    }

//    @Override
//    public Task loadTaskByAgentId(Long taskId) {
//        return this.taskRepo
//                .findBy(company)
//                .orElse(null);
//    }



    @Override
    public Task affectAgentToTask(Long idTask, Long idAgent) {
        Task task = this.loadTaskById(idTask);
        Agent agent = this.agentService.loadAgentById(idAgent);
        if(task != null){
            task.setAgent(agent);
            return this.taskRepo.save(task);
        }
        return null;
    }

    @Override
    public Task affectClientToTask(Long idTask, Long idClient) {
        Task task = this.loadTaskById(idTask);
        Client client = this.clientService.loadClientById(idClient);
        if(task != null){
            task.setClient(client);
            return this.taskRepo.save(task);
        }
        return null;
    }



    @Override
    public int countTotalTasks() {
        return (int) taskRepo.count();
    }

    @Override
    public int countScheduledTasks() {
        return taskRepo.countByStatus(TaskStatus.SCHEDULED);
    }

    @Override
    public int countDoneTasks() {
        return taskRepo.countByStatus(TaskStatus.DONE);
    }
    @Override
    public int countCanceledTasks() {
        return taskRepo.countByStatus(TaskStatus.CANCELED);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepo.findByTaskId(taskId);
        task.setStatus(status);
        return taskRepo.save(task);
    }
}
