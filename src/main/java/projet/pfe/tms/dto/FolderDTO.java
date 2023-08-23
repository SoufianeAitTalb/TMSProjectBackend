package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderDTO {
    private Long folderId;
    private String referenceClient;
    private String numRep1;
    private String openingType;
    private String description;
    private LocalDateTime dateFinished;
    private Long typeFolderId;
    private Long supplierId;
    private Long clientId;
    private Long recipientId;
    private Long customsOfficeId;
    private Long customsRegimeId;
    // private Long agentId;
}
