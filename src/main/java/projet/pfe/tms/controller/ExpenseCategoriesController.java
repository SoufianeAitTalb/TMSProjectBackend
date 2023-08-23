package projet.pfe.tms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.ExpenseCategories;
import projet.pfe.tms.models.TypeFolder;
import projet.pfe.tms.services.ExpenseCategoriesService;

import java.util.List;

@RestController
@RequestMapping("api/expensesCategories")
public class ExpenseCategoriesController {
    private final ExpenseCategoriesService expenseCategoriesService;

    public ExpenseCategoriesController(ExpenseCategoriesService expenseCategoriesService) {
        this.expenseCategoriesService = expenseCategoriesService;
    }
    @GetMapping("/")
    public List<ExpenseCategories> getAllExpenseSCategories(){

        return this.expenseCategoriesService.getAllExpenseSCategories();
    }
    @GetMapping("/{id}")
    public ExpenseCategories getExpenseCategoryById(@PathVariable Long id){
        return this.expenseCategoriesService.getExpenseCategoryById(id);
    }

}
