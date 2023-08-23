package projet.pfe.tms.services;

import projet.pfe.tms.models.Expense;
import projet.pfe.tms.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {

   List<ExpenseDTO> getAllExpenses() ;

   Expense getExpenseById(Long expenseId) ;

   Expense createExpense(ExpenseDTO expenseDTO) ;

   Expense updateExpense(Long expenseId, ExpenseDTO expenseDTO) ;

   void deleteExpense(Long expenseId) ;
}