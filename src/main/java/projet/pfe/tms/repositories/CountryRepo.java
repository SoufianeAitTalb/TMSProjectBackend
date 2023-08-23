package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Country;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {
    Country findById(long countryId);
}
