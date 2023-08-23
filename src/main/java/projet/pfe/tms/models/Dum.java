package projet.pfe.tms.models;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import projet.pfe.tms.config.response.LocalDateTimeSerializer;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dum {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "num_dum")
    private String numDum;
    
    @Column(name = "num_dum_1")
    private String numDum1;
    
    @Column(name = "num_dum_2")
    private String numDum2;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_dum")
    private LocalDateTime dateDum;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_deman")
    private LocalDateTime dateDeman;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_valid")
    private LocalDateTime dateValid;
    
    @ManyToOne(optional = false)
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "folder_id",referencedColumnName ="folder_id" ,nullable = false)
    private Folder folder;

}
