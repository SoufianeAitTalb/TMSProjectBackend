package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.ServiceLiquidation;

@Repository
public interface ServiceLiquidationRepo extends JpaRepository<ServiceLiquidation, Long> {}
