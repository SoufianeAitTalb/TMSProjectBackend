package projet.pfe.tms.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FolderInvoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_facture", nullable = false)
    private String numFacture;

    @Column(name = "montant_total", nullable = false, precision = 15, scale = 2)
    private Double montantTotal;

    @Column(name = "devise_inv", nullable = false)
    private String deviseInv;

    @Column(name = "ref_client")
    private String refClient;

   /* @Column(name = "regime_douan_inv")
    private Integer regimeDouanInv;

    @Column(name = "bureau_ded_inv")
    private Integer bureauDedInv;

    @Column(name = "regime_inv")
    private String regimeInv;*/

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Folder folder;
}
