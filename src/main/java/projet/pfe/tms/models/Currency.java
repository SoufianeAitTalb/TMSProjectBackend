package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currencyid")
    private Long currencyId;

    @Column(name = "symbol",length = 10,nullable = false)
    private String symbol;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "isdefault", nullable = false)
    private boolean isdefault = false;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    private List<Client> clients;

    @OneToMany(mappedBy = "defaultCurrency")
    @JsonIgnore
    private List<Supplier> suppliers;
}
