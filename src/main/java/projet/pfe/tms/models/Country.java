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
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryid")
    private Long countryId;

    @Column(name = "iso2", length = 2)
    private String iso2;

    @Column(name = "short_name", length = 80,nullable = false)
    private String shortName;

    @Column(name = "long_name", length = 80,nullable = false)
    private String longName;

    @Column(name = "iso3", length = 3)
    private String iso3;

    @Column(name = "numcode", length = 6)
    private String numcode;

    @Column(name = "un_member", length = 12)
    private String unMember;

    @Column(name = "calling_code", length = 8)
    private String callingCode;

    @Column(name = "cctld", length = 5)
    private String cctld;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<Client> clientsCountries;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<Agent> agentsCountries;

    @OneToMany(mappedBy = "billingCountry")
    @JsonIgnore
    private List<Client> billingCountries;

    @OneToMany(mappedBy = "shippingCountry")
    @JsonIgnore
    private List<Client> shippingCountries;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<Supplier> countries;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<Bank> countryBanks;

}
