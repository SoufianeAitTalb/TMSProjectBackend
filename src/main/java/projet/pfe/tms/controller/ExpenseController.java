package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.models.Expense;
import projet.pfe.tms.dto.ExpenseDTO;
import projet.pfe.tms.services.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public List<ExpenseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {

        return expenseService.getExpenseById(id);
    }

    @PostMapping("/add-expense")
    public ResponseEntity<String> createExpense(@RequestBody ExpenseDTO expenseDTO) {
       if (expenseService.createExpense(expenseDTO) !=null)
           return ResponseEntity.ok("Depense a été crée avec succés");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors du création de depense");
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO)
    {
        if(expenseService.updateExpense(id, expenseDTO) !=null)
            return ResponseEntity.ok("La depense a été modifié avec succés");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification du depense");
    }

    @DeleteMapping("/delete-expense/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
