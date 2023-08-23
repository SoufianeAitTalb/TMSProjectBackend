package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.enums.TaskPriority;
import projet.pfe.tms.enums.TaskRepeatEvery;
import projet.pfe.tms.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "task_details", length = 100)
    private String taskDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 100)
    private TaskStatus status = TaskStatus.SCHEDULED;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "due_date")
    private LocalDateTime dueDate ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "plan_next_action")
    private LocalDateTime planNextAction ;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 100)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeatEvery", length = 100)
    private TaskRepeatEvery repeatEvery;


    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "client_id", referencedColumnName = "userid")
    private Client client;






}
