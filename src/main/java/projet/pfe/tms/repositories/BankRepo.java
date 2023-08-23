package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {
}
