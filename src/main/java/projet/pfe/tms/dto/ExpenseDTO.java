package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.models.Client;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private Long ExpenseId;
    private Long categoryExpenseId;
    private String expNumber;
    private BigDecimal amount;
    private String payeur;
    private String categoryFacturation;
    private String comment;
   // private Long clientId;
    private Long supplierId;
    private Long folderId;
    private String numFacture;
    private String type;
    private String etat;
    private LocalDateTime date;
}
