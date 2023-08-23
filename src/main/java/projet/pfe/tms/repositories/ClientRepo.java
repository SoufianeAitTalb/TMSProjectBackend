package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.pfe.tms.models.Client;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    @Override
    Optional<Client> findById(Long id);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByCompany(String company);
    Optional<Client> findByIceClient(String iceClient);
    int countByActive(boolean active);
    Client findByClientId(Long clientId);

}

