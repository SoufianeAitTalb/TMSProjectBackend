package projet.pfe.tms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarchandiseDTO {
	
	private Long id;
	private Integer nature;
	private String nombre;
	private String marque;
	private String transType;
	private String description;
	private Integer paysProvenance;
	private String incoterms;
	private Integer typeCont;
	private String numCont;
	private BigDecimal poidb;
	private BigDecimal poidn;
	private String statusMarchandise;
	private BigDecimal volume;
	private Integer franchise;
	private String typeRetard;
	private String commentaire;
	private String conComp;
	private String conBase;
	private String compagnie;
	private String nVoyage;
	private LocalDateTime dateRec;
	private String navire;
	private LocalDateTime datePrev;
	private String chargement;
	private String hawb;
	private String vol;
	private String ltaN;
	private String magasin;
	private String dechargement;
	private Long folderId;
	private Long agrementId;
	
}
