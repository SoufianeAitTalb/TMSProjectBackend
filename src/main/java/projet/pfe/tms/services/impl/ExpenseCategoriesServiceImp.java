package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.models.ExpenseCategories;
import projet.pfe.tms.repositories.ExpenseCategoriesRepo;
import projet.pfe.tms.services.ExpenseCategoriesService;

import java.util.List;
@Service
public class ExpenseCategoriesServiceImp implements ExpenseCategoriesService {
  private final ExpenseCategoriesRepo expenseCategoriesRepo;

    public ExpenseCategoriesServiceImp(ExpenseCategoriesRepo expenseCategoriesRepo) {
        this.expenseCategoriesRepo = expenseCategoriesRepo;
    }

    @Override
    public List<ExpenseCategories> getAllExpenseSCategories() {
        return this.expenseCategoriesRepo.findAll();
    }

    @Override
    public ExpenseCategories getExpenseCategoryById(Long id) {
        return this.expenseCategoriesRepo.findById(id).orElse(null);
    }
}
