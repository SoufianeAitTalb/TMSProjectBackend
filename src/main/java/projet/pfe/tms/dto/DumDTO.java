package projet.pfe.tms.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projet.pfe.tms.config.response.LocalDateTimeSerializer;
import projet.pfe.tms.models.Folder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DumDTO {
	
    private String numDum;
    private LocalDateTime dateDum;
    private LocalDateTime dateDeman;
    private LocalDateTime dateValid;
    private Long folderId;


}
