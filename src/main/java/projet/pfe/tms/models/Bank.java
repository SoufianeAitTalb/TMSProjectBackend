package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "name", length = 255, nullable = false)
    private String bankName;

    @Column(name = "type", length = 255, nullable = false)
    private String bankType;

    @Column(name = "exploitation")
    private boolean exploitation;

    @Column(name = "payment")
    private boolean payment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "country_id", referencedColumnName = "countryid")
    private Country country;

    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<TypeLiquidation> TypeLiquidationList;

}
