package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiquidationDTO {

    private String liquidationType;
    private String admisConforme;
    private String commis;
    private String inspecteur;

    private boolean mainLeve;
    private LocalDateTime dateMainLeve;
    private boolean visiteDouaniere;
    private LocalDateTime dateProgrammationVisite;
    private boolean bureauValeur;
    private LocalDateTime dateProgrammationBureau;

    private String onssa;
    private LocalDateTime dateProgrammationOnssa;
    private LocalDateTime dateVisiteOnssa;
    private LocalDateTime dateValidationOnssa;
    private String mcia;
    private LocalDateTime dateProgrammationMcia;
    private LocalDateTime dateVisiteMcia;
    private LocalDateTime dateValidationMcia;

    private String apurement;
    private LocalDate dateBon;
    private LocalDate dateRemiseBon;

}
