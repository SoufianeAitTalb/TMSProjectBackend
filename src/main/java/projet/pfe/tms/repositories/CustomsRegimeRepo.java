package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.CustomsRegime;

@Repository
public interface CustomsRegimeRepo extends JpaRepository<CustomsRegime, Long> {
}

