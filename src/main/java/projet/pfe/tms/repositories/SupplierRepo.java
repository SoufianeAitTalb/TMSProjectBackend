package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Supplier;


@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long>
  {
    Supplier findByUserId(Long userId);
    Supplier findSupplierByCompany(String company);
    int countByActive(boolean active);

  }
