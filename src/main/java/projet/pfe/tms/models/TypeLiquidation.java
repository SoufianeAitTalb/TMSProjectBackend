package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "liquidations")
public class TypeLiquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_liquidation_id")
    private Long typeLiquidationId;

    @Column(name = "mode_liquidation", length = 100)
    private String modeLiquidation;

    @Column(name = "n_cheque", length = 20)
    private String numeroCheque;

    @Column(name = "date_emission")
    private LocalDateTime dateEmission;

    @Column(name = "date_reception")
    private LocalDateTime dateReception;

    @Column(name = "date_echeance_paiement")
    private LocalDateTime dateEcheancePaiement;

    @Column(name = "quittance", length = 50)
    private String quittance;

    @Column(name = "num_fl", length = 50)
    private String numFl;

    @Column(name = "date_echeance")
    private LocalDateTime dateEcheance;

    @Column(name = "date_fl")
    private LocalDateTime dateFl;

    @Column(name = "montant_total")
    private Double montantTotal;

    @Column(name = "interet_retard", length = 5)
    private String interetRetard;

    @Column(name = "montant_interet")
    private Double montantInteret;

    @Column(name = "paye_par", length = 50)
    private String payePar;

    @Lob
    @Column(name = "comment_liquidation", columnDefinition = "TEXT")
    private String commentLiquidation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "bank_id", referencedColumnName = "bank_id")
    private Bank bank;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "folder_id", referencedColumnName = "folder_id")
    private Folder folder;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "folder_liquidation_id", referencedColumnName = "liquidation_id")
    private FolderLiquidation folderLiquidation;

}
