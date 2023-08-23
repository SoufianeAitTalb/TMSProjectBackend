package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "folder_liquidation")
public class FolderLiquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liquidation_id")
    private Long liquidationId;

    @Column(name = "liquidation_type")
    private String liquidationType;

    @Column(name = "admis_conforme")
    private String admisConforme;

    @Column(name = "commis")
    private String commis;

    @Column(name = "inspecteur")
    private String inspecteur;

    @Column(name = "main_leve")
    private boolean mainLeve = false;

    @Column(name = "date_main_leve")
    private LocalDateTime dateMainLeve;

    @Column(name = "visite_douaniere")
    private boolean visiteDouaniere = false;

    @Column(name = "date_programmation_vis")
    private LocalDateTime dateProgrammationVisite;

    @Column(name = "bureau_valeur")
    private boolean bureauValeur = false;

    @Column(name = "date_programmation_bur")
    private LocalDateTime dateProgrammationBureau;

    @Column(name = "onssa")
    private String onssa;

    @Column(name = "date_programmation_onssa")
    private LocalDateTime dateProgrammationOnssa;

    @Column(name = "date_visite_onssa")
    private LocalDateTime dateVisiteOnssa;

    @Column(name = "date_validation_onssa")
    private LocalDateTime dateValidationOnssa;

    @Column(name = "mcia")
    private String mcia;

    @Column(name = "date_programmation_mcia")
    private LocalDateTime dateProgrammationMcia;

    @Column(name = "date_visite_mcia")
    private LocalDateTime dateVisiteMcia;

    @Column(name = "date_validation_mcia")
    private LocalDateTime dateValidationMcia;

    @Column(name = "apurement")
    private String apurement;

    @Column(name = "date_bon_del")
    private LocalDate dateBon;

    @Column(name = "date_remise_bon")
    private LocalDate dateRemiseBon;

    @Column(name = "consignation_montant")
    private String consignationMontant;

    @Column(name = "consignation_date")
    private LocalDate consignationDate;

//    @Column(name = "date_vis_1")
//    private LocalDate dateVis1;
//
//    @Column(name = "date_vis_2")
//    private LocalDate dateVis2;

    @Column(name = "cat_commerciaux")
    private String catCommerciaux;

    @Column(name = "commission_valeur")
    private String commissionValeur;

    @Column(name = "poids_mesure")
    private String poidsMesure;

    @Column(name = "anrt")
    private String anrt;

    @Column(name = "date_demande_service_bur")
    private LocalDate dateDemandeServiceBureau;

    @Column(name = "val_acc")
    private String valAcc;

    @Column(name = "status_liquidation")
    private String statusLiquidation;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "folder_id", referencedColumnName = "folder_id")
    private Folder folder;

    @OneToOne(mappedBy = "folderLiquidation",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH },
            fetch = FetchType.EAGER)
    private TypeLiquidation typeLiquidation;

}
