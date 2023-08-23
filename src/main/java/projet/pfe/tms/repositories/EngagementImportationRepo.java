package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.EngagementImportation;

import java.util.List;

@Repository
public interface EngagementImportationRepo extends JpaRepository<EngagementImportation, Long> {
    List<EngagementImportation>  findByFolder_FolderId(Long folder_id);
}
