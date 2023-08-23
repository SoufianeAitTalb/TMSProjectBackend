package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expense_categories")
public class ExpenseCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_expense_id")
    private Long categoryExpenseId;

    @Column(name = "category_expense_name", length = 100)
    private String categoryExpenseName;

    @OneToMany(mappedBy = "categoryExpense", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIgnore
    private Set<Expense> expenses;
}
