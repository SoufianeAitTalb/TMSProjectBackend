package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agent", indexes = {
        @Index(name = "company_idx", columnList = "company"),
        @Index(name = "country_idx", columnList = "country_id")
})
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "company", length = 100)
    private String company;

    @Column(name = "phonenumber", length = 30)
    private String phoneNumber;

    @Column(name = "agent_email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "website", length = 150)
    private String website;

    @Column(name = "cnss", length = 15)
    private String CNSS;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "zip", length = 10)
    private String zip;

    @Column(name = "patente", precision = 15, scale = 2)
    private Double patente;

    @Column(name = "state", length = 100)
    private String state;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "billing_street", length = 200)
    private String billingStreet;

    @Column(name = "billing_state", length = 100)
    private String billingState;

    @Column(name = "billing_city", length = 100)
    private String billingCity;

    @Column(name = "billing_zip", length = 100)
    private String billingZip;

    @Column(name = "shipping_street", length = 200)
    private String shippingStreet;

    @Column(name = "shipping_state", length = 100)
    private String shippingState;

    @Column(name = "shipping_city", length = 100)
    private String shippingCity;

    @Column(name = "shipping_zip", length = 100)
    private String shippingZip;

    @Column(name = "longitude", length = 300)
    private String longitude;

    @Column(name = "latitude", length = 300)
    private String latitude;

    @Column(name = "default_language", length = 40)
    private String defaultLanguage;

    @Column(name = "show_primary_contact")
    @Value("${statut:false}")
    private boolean showPrimaryContact;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Column(name = "date_affectation_of_commercial")
    private LocalDateTime DateAffectationOfCommercial;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "country_id", referencedColumnName = "countryid")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "billing_country_id", referencedColumnName = "countryid")
    private Country billingCountry;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "shipping_country_id", referencedColumnName = "countryid")
    private Country shippingCountry;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "currency_id", referencedColumnName = "currencyid")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "staff_id", referencedColumnName = "staffid")
    private Staff staff;


    @OneToMany(mappedBy = "agent")
    @JsonIgnore
    private List<Ngp> ngp;

    @OneToMany(mappedBy = "agent", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIgnore
    private Set<Folder> agentFolders;

    @OneToMany(mappedBy = "agent")
    @JsonIgnore
    private List<Task> tasks;

}
