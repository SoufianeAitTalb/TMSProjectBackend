package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tblengagement_importation")
public class EngagementImportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_eng")
    private String numEng;

    @Column(name = "banque_eng")
    private String banqueEng;

    @Column(name = "poidn_eng")
    private String poidnEng;

    @Column(name = "valeur_totale_eng")
    private String valeurTotaleEng;

    @Column(name = "num_facture_eng")
    private String numFactureEng;

    @Column(name = "effectue_par")
    private String effectuePar;

    @Column(name = "date_echeance_en")
    private LocalDate dateEcheanceEn;

    @Column(name = "date_domicialisation_en")
    private LocalDate dateDomicialisationEn;

    @Column(name = "autre_devise")
    private Integer autreDevise;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Folder folder;


}
