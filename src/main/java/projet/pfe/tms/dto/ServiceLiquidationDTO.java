package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceLiquidationDTO {

    private String serviceName;
    private String confirme;
    private LocalDateTime dateDemandeService;
    private LocalDateTime dateValidation;

}
