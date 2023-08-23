package projet.pfe.tms.services;

import projet.pfe.tms.models.ExpenseCategories;

import java.util.List;

public interface ExpenseCategoriesService {

    List<ExpenseCategories> getAllExpenseSCategories();

    ExpenseCategories getExpenseCategoryById(Long id);
}
