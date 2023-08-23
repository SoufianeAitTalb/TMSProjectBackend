package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.FolderLiquidation;

@Repository
public interface FolderLiquidationRepo extends JpaRepository<FolderLiquidation, Long> {
}
