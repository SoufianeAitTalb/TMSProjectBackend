package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.AgentDTO;
import projet.pfe.tms.models.Agent;
import projet.pfe.tms.services.AgentService;

import java.util.List;

@CrossOrigin("**")
@RestController
@RequestMapping("api/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    @GetMapping("/")
    public List<Agent> getAllAgents(){
        return this.agentService.listAgents();
    }
    @GetMapping("/dto")
    public List<AgentDTO> getAllAgentsDTO(){
        return this.agentService.listAgentsDTO();
    }

    @GetMapping("/{id}")
    public AgentDTO getAgentById(@PathVariable Long id){
        return this.agentService.loadAgentByAgentId(id);
    }

    @PostMapping("/add-agent")
    public ResponseEntity<String> addNewAgent(@RequestBody AgentDTO agentDto){
        if (this.agentService.loadAgentByEmail(agentDto.getEmail()) != null)
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé");

        if(this.agentService.addNewAgent(agentDto) != null)
            return ResponseEntity.ok("Le agent a été ajouté avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du agent");
    }

    @PutMapping("/update-agent/{id}")
    public ResponseEntity<String> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDto){

        if(this.agentService.updateAgent(id, agentDto) != null )
            return ResponseEntity.ok("L'agent a été modifié avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification d'agent");
    }

    @DeleteMapping("/delete-agent/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id){
        this.agentService.deleteAgent(id);
        return ResponseEntity.ok("L'agent a été supprimé avec succès");
    }

    @PutMapping("/update-agent/{id}/affect-commercial/{staffId}")
    public ResponseEntity<String> affectCommercialToAgent(@PathVariable Long id, @PathVariable Long staffId){
        if(this.agentService.affectCommercialToAgent(id, staffId) != null )
            return ResponseEntity.ok("Le commercial a été affecté à l'agent avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'affectation du commercial à l'agent");
    }

    @DeleteMapping("/update-agent/{id}/delete-commercial")
    public ResponseEntity<String> deleteCommercialOfAgent(@PathVariable Long id){
        if(this.agentService.deleteCommercialOfAgent(id) != null)
            return ResponseEntity.ok("Le commercial a été supprimé avec succès");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la suppression du commercial");
    }

    @GetMapping("/Total-Agents")
    public int countTotalAgents() {
        return this.agentService.countTotalAgents();
    }

    @GetMapping("/Total-Active-Agents")
    public int countActiveAgents() {
        return this.agentService.countActiveAgents();
    }

    @GetMapping("/Total-Inactive-Agents")
    public int countInactiveAgents() {
        return this.agentService.countInactiveAgents();
    }

    @PutMapping("/{agentId}/active")
    public ResponseEntity<String> updateAgentActiveStatus(@PathVariable Long agentId, @RequestParam boolean active) {
        Agent agent = agentService.updateAgentActiveStatus(agentId, active);
        if (agent != null) {
            return ResponseEntity.ok("Le statut d'agent a été mis à jour avec succès");
        } else {
            return ResponseEntity.badRequest().body("Echec de la mise à jour du statut d'agent");
        }
    }
}
