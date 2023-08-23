package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.CustomsOffice;

@Repository
public interface CustomsOfficeRepo extends JpaRepository<CustomsOffice, Long>  {
}


