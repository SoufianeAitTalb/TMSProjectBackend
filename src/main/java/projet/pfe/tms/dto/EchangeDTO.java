package projet.pfe.tms.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EchangeDTO {
    private String coursier;
    private String statusEchange;
    private String natureEchange; 
    private  LocalDateTime dateReceBad;
    private  LocalDateTime dateLettreRes;
    private  LocalDateTime dateReceCheque;
    private  LocalDateTime dateRemiseCheque;
    private Long folderId;

}
