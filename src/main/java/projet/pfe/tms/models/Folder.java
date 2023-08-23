package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "folder", indexes = {
                @Index(name = "name_idx", columnList = "name"),
                @Index(name = "client_idx", columnList = "client_id"),
                @Index(name = "supplier_idx", columnList = "supplier_id"),
                @Index(name = "recipient_idx", columnList = "recipient_id")
})
public class Folder {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "folder_id")
        private Long folderId;

        @Column(name = "name", length = 100)
        private String name;

        @Column(name = "reference_client", length = 255)
        private String referenceClient;

        @Column(name = "num_rep1", length = 100)
        private String numRep1;

        @Column(name = "openning_type", length = 100)
        private String openingType;

        @Lob
        @Column(name = "description", length = Integer.MAX_VALUE)
        private String description;

        @Column(name = "start_date")
        private LocalDate startDate;

        @Column(name = "deadline")
        private LocalDate deadline;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Column(name = "date_finished")
        private LocalDateTime dateFinished;

        @Column(name = "date_created", nullable = false)
        private LocalDateTime dateCreated = LocalDateTime.now();

        @Column(name = "status")
        private boolean status = false;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JoinColumn(name = "supplier_id", referencedColumnName = "userid")
        private Supplier supplier;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JoinColumn(name = "client_id", referencedColumnName = "userid")
        private Client client;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })

        @JoinColumn(name = "recipient_id", referencedColumnName = "recipient_id")
        private Recipient recipient;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })

        @JoinColumn(name = "type_folder_id", referencedColumnName = "type_folder_id")
        private TypeFolder typeFolder;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })

        @JoinColumn(name = "customsoffice_id", referencedColumnName = "customs_office_id")
        private CustomsOffice customsOffice;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JoinColumn(name = "customsregime_id", referencedColumnName = "customs_regime_id")
        private CustomsRegime customsRegime;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude

        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JsonIgnore
        @JoinColumn(name = "agent_id", referencedColumnName = "agent_id")
        private Agent agent;

        @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<FolderInvoices> Finvoices = new ArrayList<>();

        @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<EngagementImportation> EngagementImportation = new ArrayList<>();

        @OneToMany(mappedBy = "folder",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties
        @JsonIgnore
        private List<Echange> echange;

        @OneToMany(mappedBy = "folder",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties
        @JsonIgnore
        private List<Marchandise> marchandise;

        @OneToMany(mappedBy = "folder",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties
        @JsonIgnore
        private List<Dum> dum;

        @OneToMany(mappedBy = "folder",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties
        @JsonIgnore
        private List<ServiceLiquidation> ServicesLiquidationList;

        @OneToOne(mappedBy = "folder",
                  cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH },
                  fetch = FetchType.EAGER)
        private FolderLiquidation folderLiquidation;

        @OneToOne(mappedBy = "folder",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH },
            fetch = FetchType.EAGER)
        private TypeLiquidation typeLiquidation;

        @OneToMany(mappedBy = "folder", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.REFRESH, CascadeType.DETACH })
        @JsonIgnore
        private List<Expense> folderExpenses;

}
