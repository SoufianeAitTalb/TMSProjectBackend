package projet.pfe.tms.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;
import lombok.NoArgsConstructor;
import projet.pfe.tms.config.response.LocalDateTimeSerializer;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Echange {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "coursier", length = 255)
    private String coursier;
    
    @Column(name="status_echange", length= 255)
    private String statusEchange;
    
    @Column(name="nature_echange", length= 255)
    private String natureEchange; 
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name="date_rece_bad")
    private  LocalDateTime dateReceBad;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name="date_lettre_res")
    private  LocalDateTime dateLettreRes;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name="date_rece_cheque")
    private  LocalDateTime dateReceCheque;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name="date_remise_cheque")
    private  LocalDateTime dateRemiseCheque;

    
    @ManyToOne(optional = false)
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "folder_id",referencedColumnName ="folder_id" ,nullable = false)
    private Folder folder;
}
