package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderInvoicesDTO {
    private String numFacture;
    private Double montantTotal;
    private String deviseInv;
    private String refClient;
    //private Integer regimeDouanInv;
    //private Integer bureauDedInv;
    //private String regimeInv;
    private long folder_id;
}
