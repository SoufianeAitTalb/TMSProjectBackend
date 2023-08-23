package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ngp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ngpid")
    private Long ngpId;

    @Column(name = "codeProduct", unique = true,nullable = false ,length = 100)
    private String codeProduct;

    @Column(name = "desi", length = 100)
    private String desi;

    @Column(name = "dossier ", length = 100)
    private String dossier;

    @Column(name = "ngp ", length = 100)
    private String Ngp;


    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "supplierid", referencedColumnName = "userid")
    private Supplier supplier ;

    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "clientid", referencedColumnName = "userid")
    private Client client;

    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
    private Agent agent;

    @Column(name = "origine ", length = 100)
    private String origine;

    @Column(name = "date_created", length = 100)
    private Date date_created;




}
