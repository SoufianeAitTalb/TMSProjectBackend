package projet.pfe.tms.services;

import projet.pfe.tms.dto.AgentDTO;
import projet.pfe.tms.models.Agent;

import java.util.List;

public interface AgentService {

    Agent addNewAgent(AgentDTO agentDto);
    Agent updateAgent(Long id, AgentDTO agentDTO);
    Agent updateData(Agent Agent, AgentDTO agentDto);
    void deleteAgent(Long id);
    List<AgentDTO> listAgentsDTO();
    List<Agent> listAgents();
    Agent loadAgentByEmail(String email);
    Agent loadAgentById(Long id);
    AgentDTO loadAgentByAgentId(Long id);
    Agent loadAgentByCompany(String company);

    Agent affectCommercialToAgent(Long idAgent, Long idStaff);
    Agent deleteCommercialOfAgent(Long id);

    int countTotalAgents();
    int countActiveAgents();
    int countInactiveAgents();
    Agent   updateAgentActiveStatus(Long AgentId, boolean active);

}
