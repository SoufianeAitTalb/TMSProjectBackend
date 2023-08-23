package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "liquidation_services")
public class ServiceLiquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_liquidation_id")
    private Long serviceLiquidationId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "confirme", length = 3)
    private String confirme;

    @Column(name = "date_demande_service")
    private LocalDateTime dateDemandeService;

    @Column(name = "date_programmation")
    private LocalDateTime dateProgrammation;

    @Column(name = "date_validation")
    private LocalDateTime dateValidation;

    @ManyToOne(fetch = FetchType.EAGER,
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "folder_id", referencedColumnName = "folder_id")
    private Folder folder;

}
