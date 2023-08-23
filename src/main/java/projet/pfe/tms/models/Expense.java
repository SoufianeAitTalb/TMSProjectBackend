package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "expenses", indexes = {
        @Index(name = "supplier_idx", columnList = "supplier_id"),
        @Index(name = "folder_idx", columnList = "folder_id")
})

    public class Expense {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_expenses")
        private Long ExpenseId;

        @Column(name = "exp_number")
        private String expNumber;

        @Column(name = "amount")
        private BigDecimal amount;

        @Column(name = "payer")
        private String payeur;

        @Column(name = "category_invoice")
        private String categoryFacturation;

        @Column(name = "supplier_invoiceid")
        private Integer supplierInvoiceId;

        @Column(name = "comment",length = Integer.MAX_VALUE)
        private String comment;

      /*  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JoinColumn(name = "client_id", referencedColumnName = "userid")
        private Client client;
       */
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JsonIgnore
        @JoinColumn(name = "folder_id", referencedColumnName = "folder_id")
        private Folder folder;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JsonIgnore
        @JoinColumn(name = "supplier_id", referencedColumnName = "userid")
        private Supplier supplier;

        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
        @JsonIgnore
        @JoinColumn(name = "category_expense", referencedColumnName = "category_expense_id")
        private ExpenseCategories categoryExpense;

        @Column(name = "num_facture")
        private String numFacture;

        @Column(name = "type")
        private String type;

        @Column(name = "etat")
        private String etat;

        @Column(name = "date", nullable = false)
        private LocalDateTime date= LocalDateTime.now();

    }

