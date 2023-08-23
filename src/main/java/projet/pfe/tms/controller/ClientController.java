package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.services.ClientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/clients")
public class ClientController { 

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getAllClients(){
        return this.clientService.listClients();
    }
    @GetMapping("/dto")
    public List<ClientDTO> getAllClientsDTO(){
        return this.clientService.listClientsDTO();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return this.clientService.loadClientByClientId(id);
    }

   
    @PostMapping("/add-client")
    public ResponseEntity<Map<String, String>> addNewClient(@RequestBody ClientDTO clientDto) {
    Map<String, String> response = new HashMap<>();

    if (this.clientService.loadClientByEmail(clientDto.getEmail()) != null) {
        response.put("status", "error");
        response.put("message", "Cet email est déjà utilisé");
        return ResponseEntity.badRequest().body(response);
    }

    if (this.clientService.addNewClient(clientDto) != null) {
        response.put("status", "success");
        response.put("message", "Le client a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout du client");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-client/{id}")
public ResponseEntity<Map<String, String>> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto) {
    Map<String, String> response = new HashMap<>();


    if(this.clientService.updateClient(id, clientDto) != null ) {
        response.put("status", "success");
        response.put("message", "Le client a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification du client");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long id){
        this.clientService.deleteClient(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Le client a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }

    @PutMapping("/update-client/{id}/affect-commercial/{staffId}")
    public ResponseEntity<String> affectCommercialToClient(@PathVariable Long id, @PathVariable Long staffId){
        if(this.clientService.affectCommercialToClient(id, staffId) != null )
            return ResponseEntity.ok("Le commercial a été affecté au client avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'affectation du commercial au client");
    }

    @DeleteMapping("/update-client/{id}/delete-commercial")
    public ResponseEntity<String> deleteCommercialOfClient(@PathVariable Long id){
        if(this.clientService.deleteCommercialOfClient(id) != null)
            return ResponseEntity.ok("Le commercial a été supprimé avec succès");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la suppression du commercial");
    }

    @GetMapping("/Total-Clients")
    public int countTotalClients() {
        return this.clientService.countTotalClients();
    }

    @GetMapping("/Total-Active-Clients")
    public int countActiveClients() {
        return this.clientService.countActiveClients();
    }

    @GetMapping("/Total-Inactive-Clients")
    public int countInactiveClients() {
        return this.clientService.countInactiveClients();
    }

    @PutMapping("/{clientId}/active")
    public ResponseEntity<String> updateClientActiveStatus(@PathVariable Long clientId, @RequestParam boolean active) {
        Client client = clientService.updateClientActiveStatus(clientId, active);
        if (client != null) {
            return ResponseEntity.ok("Le statut de client a été mis à jour avec succès");
        } else {
            return ResponseEntity.badRequest().body("Echec de la mise à jour du statut de client");
        }
    }
}
