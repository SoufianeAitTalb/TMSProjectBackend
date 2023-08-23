package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Agent;

import java.util.Optional;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long> {

    @Override
    Optional<Agent> findById(Long id);
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByCompany(String company);

    int countByActive(boolean active);
    Agent findByAgentId(Long agentId);

}

