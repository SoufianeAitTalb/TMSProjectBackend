package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.FolderInvoices;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderInvoicesRepo extends JpaRepository<FolderInvoices, Long> {
    List<FolderInvoices> findByFolder_FolderId(Long folder_id);
}
