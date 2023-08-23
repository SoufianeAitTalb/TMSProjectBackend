package projet.pfe.tms.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.*;

import java.util.List;

@Repository
public interface FolderRepo extends JpaRepository<Folder, Long> {

    List<Folder> findFolderByRecipient(Recipient recipient);

    List<Folder> findFolderBySupplier(Supplier supplier);

    List<Folder> findFolderByClient(Client client);

    List<Folder> findFolderByAgent(Agent agent);
}
