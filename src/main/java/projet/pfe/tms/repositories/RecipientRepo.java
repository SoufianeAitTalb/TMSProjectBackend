package projet.pfe.tms.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projet.pfe.tms.models.Recipient;

import java.util.List;

public interface RecipientRepo extends JpaRepository<Recipient, Long> {

    @Query("SELECT r FROM Recipient r JOIN FETCH r.recipientFolders f WHERE f.folderId = :folderId")
    List<Recipient> findByFolderId(@Param("folderId") Long folderId);

}
