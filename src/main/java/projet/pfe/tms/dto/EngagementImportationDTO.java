package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import projet.pfe.tms.models.Folder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EngagementImportationDTO {


    private String numEng;
    private String banqueEng;
    private String poidnEng;
    private String valeurTotaleEng;
    private String numFactureEng;
    private String effectuePar;
    private LocalDate dateEcheanceEn;
    private LocalDate dateDomicialisationEn;
    private Integer autreDevise;
    private long folder_id;

}
