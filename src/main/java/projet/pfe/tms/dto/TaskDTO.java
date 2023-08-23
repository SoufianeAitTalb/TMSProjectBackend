package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.enums.TaskPriority;
import projet.pfe.tms.enums.TaskRepeatEvery;
import projet.pfe.tms.enums.TaskStatus;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long taskId;
    private String name;
    private String taskDetails;
    private TaskStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime planNextAction ;
    private TaskPriority priority;
    private TaskRepeatEvery repeatEvery;
    private Long agentId;
    private Long clientId;


}
