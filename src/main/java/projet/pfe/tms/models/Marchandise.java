package projet.pfe.tms.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.config.response.LocalDateTimeSerializer;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marchandise {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	

    @Column(name = "nature")
    private Integer nature;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "marque")
    private String marque;

    @Column(name = "trans_type", length = 255)
    private String transType;

    @Column(name = "descr")
    private String description;

    @Column(name = "pays_provenance")
    private Integer paysProvenance;

    @Column(name = "incoterms", length = 100)
    private String incoterms;

    @Column(name = "type_cont")
    private Integer typeCont;

    @Column(name = "num_cont", length = 255)
    private String numCont;

    @Column(name = "poidb", precision = 15, scale = 2)
    private BigDecimal poidb;

    @Column(name = "poidn", precision = 15, scale = 2)
    private BigDecimal poidn;

    @Column(name = "status_marchandise", length = 255)
    private String statusMarchandise;

    @Column(name = "volume", precision = 15, scale = 2)
    private BigDecimal volume;

    @Column(name = "franchise")
    private Integer franchise;

    @Column(name = "type_retard", length = 255)
    private String typeRetard;

    @Column(name = "commentaire")
    private String commentaire;
    
    @Column(name = "con_comp",length = 255 )
    private String conComp;

    @Column(name = "con_base",length = 255)
    private String conBase;

    @Column(name = "compagnie",length = 255)
    private String compagnie;

    @Column(name = "n_voyage", length = 255)
    private String nVoyage;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_rec")
    private LocalDateTime dateRec;

    @Column(name = "navire",length = 255 )
    private String navire;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "date_prev")
    private LocalDateTime datePrev;

    @Column(name = "chargement",length = 255 )
    private String chargement;

    @Column(name = "hawb",length = 255 )
    private String hawb;

    @Column(name = "vol",length = 255)
    private String vol;

    @Column(name = "lta_n",length = 255 )
    private String ltaN;

    @Column(name = "magasin",length = 255)
    private String magasin;
    
    @Column(name = "dechargement",length = 255 )
    private String dechargement;
    
    @ManyToOne(optional = false)
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "folder_id",referencedColumnName ="folder_id" ,nullable = false)
    private Folder folder;
    
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agrement_id")
	@JsonIgnore
    private Agrement agrement;

}
