package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.models.Bank;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.FolderLiquidation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeLiquidationDTO {

    private String modeLiquidation;
    private LocalDateTime dateEmission;
    private LocalDateTime dateReception;
    private String numeroCheque;
    private String quittance;
    private String numFl;
    private LocalDateTime dateFl;
    private Double montantTotal;
    private LocalDateTime dateEcheance;
    private String interetRetard;
}
