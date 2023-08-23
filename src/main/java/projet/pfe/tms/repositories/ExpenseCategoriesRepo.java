package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.ExpenseCategories;

@Repository
public interface ExpenseCategoriesRepo extends JpaRepository<ExpenseCategories, Long> {

}
