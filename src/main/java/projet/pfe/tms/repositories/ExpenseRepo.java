package projet.pfe.tms.repositories;

import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

}

