package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import projet.pfe.tms.config.response.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier", indexes = { @Index(name = "company_idx", columnList = "company") })

public class Supplier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "userid")
        private Long userId;

        @Column(name = "company", length = 100)
        private String company;

        @Column(name = "vat", length = 50)
        private String vat;

        @Column(name = "phonenumber", length = 30)
        private String phoneNumber;

        @Column(name = "supplier_email", length = 255)
        private String supplierEmail;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
                        CascadeType.DETACH })
        @JsonIgnore
        @JsonBackReference
        @JoinColumn(name = "country", referencedColumnName = "countryid")
        private Country country;

        @Column(name = "city", length = 100)
        private String city;

        @Column(name = "zip", length = 15)
        private String zip;

        @Column(name = "state", length = 50)
        private String state;

        @Column(name = "address", length = 100)
        private String address;

        @Column(name = "website", length = 150)
        private String website;

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @Column(name = "datecreated")
        private LocalDateTime dateCreated = LocalDateTime.now();

        @Column(name = "active")
        private Boolean active = true;

        @Column(name = "billing_street", length = 200)
        private String billingStreet;

        @Column(name = "billing_city", length = 100)
        private String billingCity;

        @Column(name = "billing_state", length = 100)
        private String billingState;

        @Column(name = "billing_zip", length = 100)
        private String billingZip;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
                        CascadeType.DETACH })
        @JsonIgnore
        @JoinColumn(name = "billing_country", referencedColumnName = "countryid")
        private Country billingCountry;

        @Column(name = "shipping_street", length = 200)
        private String shippingStreet;

        @Column(name = "shipping_city", length = 100)
        private String shippingCity;

        @Column(name = "shipping_state", length = 100)
        private String shippingState;

        @Column(name = "shipping_zip", length = 100)
        private String shippingZip;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
                        CascadeType.DETACH })
        @JoinColumn(name = "shipping_country", referencedColumnName = "countryid")
        private Country shippingCountry;

        @Column(name = "longitude", length = 300)
        private String longitude;

        @Column(name = "latitude", length = 300)
        private String latitude;

        @Column(name = "default_language", length = 40)
        private String defaultLanguage;

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
                        CascadeType.DETACH })
        @JsonIgnore
        @JsonBackReference
        @JoinColumn(name = "default_currency", referencedColumnName = "currencyid")
        private Currency defaultCurrency;

        @Column(name = "show_primary_contact")
        @Value("${statut:false}")
        private Boolean showPrimaryContact;

        @Column(name = "stripe_id", length = 40)
        private String stripeId;

        @Column(name = "registration_confirmed")
        private Boolean registrationConfirmed;

        @Column(name = "addedfrom")
        @Value("${statut:false}")
        private Boolean addedFrom;

        @Column(name = "ice_client", length = 30)
        private String iceClient;

        @Column(name = "code_auxi", length = 255, nullable = false)
        private String codeAuxi;

        @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)

        @JsonIgnoreProperties
        private List<SupplierContact> supplierContactList;

        @OneToMany(mappedBy = "supplier")
        @JsonIgnoreProperties
        private List<ClientSupplier> clientsupplier;

        @OneToMany(mappedBy = "supplier")

        @JsonIgnoreProperties
        private List<Ngp> ngp;

        @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
                        CascadeType.DETACH })
        private Set<Folder> supplierFolders;

        @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.REFRESH, CascadeType.DETACH })
        @JsonIgnore
        private List<Expense> supplierExpenses;

}
