package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.ContactClient;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactClientRepo extends JpaRepository<ContactClient, Long> {
    Optional<ContactClient> findByEmail(String email);
    List<ContactClient> findByClient_ClientId(Long clientId);
    ContactClient findByContactId(Long contactId);
    int countByActive(boolean Active);
}
