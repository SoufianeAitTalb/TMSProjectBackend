package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.ContactClientDTO;
import projet.pfe.tms.models.ContactClient;
import projet.pfe.tms.services.ContactClientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/contacts-client")
public class ContactClientController {

    private final ContactClientService contactClientService;

    @Autowired
    public ContactClientController(ContactClientService contactClientService){
        this.contactClientService = contactClientService;
    }

    @GetMapping("/")
    public List<ContactClient> getAllContactsOfClient(){
        return this.contactClientService.listContacts();
    }

    @GetMapping("/{id}")
    public ContactClientDTO getContactByClientId(@PathVariable Long id){
        return this.contactClientService.loadContactByClientId(id);
    }
    

    @PostMapping("/add-contact")
    public ResponseEntity<String> addNewContact(@RequestBody ContactClientDTO contactDto){

        if(this.contactClientService.loadContactByEmail(contactDto.getEmail()) != null)
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé");

        if(this.contactClientService.addNewContact(contactDto) != null)
            return ResponseEntity.ok("Le contact a été ajouté avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du contact");

    }

    @PutMapping("/update-contact/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Long id, @RequestBody ContactClientDTO contactDto){

        if(this.contactClientService.updateContact(id, contactDto) != null)
            return ResponseEntity.ok("Le contact a été modifié avec succès");

        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification du contact");
    }

    @DeleteMapping("/delete-contact/{id}")
    public ResponseEntity<Map<String, String>> deleteContact(@PathVariable Long id){
        this.contactClientService.deleteClient(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Le contact a été supprimé avec succès");
        return ResponseEntity.ok(response);
        
    }
// add a contact here 
    @PostMapping("/client/{id}/contacts")
    public ResponseEntity<Map<String, String>> addClientContactByClientId(@PathVariable Long id, @RequestBody ContactClientDTO contactDto) {
        contactDto.setClientId(id);
        Map<String, String> response = new HashMap<>();
        if (contactClientService.addClientContact(contactDto) != null) {
            response.put("status", "success");
            response.put("message", "Le contact de client a été ajouté avec succès");
        return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Une erreur s'est produite lors de l'ajout du contact");
            return ResponseEntity.badRequest().body(response);
        }
    }
// update a contact here 
    @PutMapping("/update-client-contact/{clientId}/{id}")
    public ResponseEntity<Map<String, String>> updateClientContact(@PathVariable Long clientId, @PathVariable Long id, @RequestBody  ContactClientDTO contactDto) {
        Map<String, String> response = new HashMap<>();
        if (contactClientService.updateClientContact(clientId, id, contactDto) != null) {
            response.put("status", "success");
            response.put("message", "Le contact a été modifié avec succès");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Une erreur s'est produite lors de la modification du contact");
            return ResponseEntity.badRequest().body(response);
        }
    }
// delete a contact heere
    @DeleteMapping("/delete-client-contact/{id}")
    public ResponseEntity<Map<String, String>> deleteClientContact(@PathVariable Long id) {
        contactClientService.deleteClientContact(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Le contact a été supprimé avec succès");
        return ResponseEntity.ok(response);
        //return ResponseEntity.ok("Client contact deleted succesfully");
    }

    @GetMapping("/contact-principal-fullname/{id}")
    public  ResponseEntity<String> getContactPrincipalFullName(@PathVariable Long id) {
        String fullName =this.contactClientService.getContactPrincipalFullName(id);
        if (fullName == null) {
            return ResponseEntity.badRequest().body("Primary contact not found for supplier " + id);
        } else {
            return ResponseEntity.ok(fullName);
        }
    }

    @GetMapping("/contact-principal-email/{id}")
    public ResponseEntity<?> getContactPrincipalEmail(@PathVariable Long id) {
        String email = this.contactClientService.getContactPrincipalEmail(id);
        if (email == null) {
            return ResponseEntity.badRequest().body("No contact found for supplier " + id);
        } else {
            return ResponseEntity.ok(email);
        }
    }

    @GetMapping("/client/{id}/contacts")
    public ResponseEntity<List<ContactClient>> getAllContactsByClient(@PathVariable Long id) {
        List<ContactClient> clientContactsList = contactClientService.getAllContactsByClient(id);
        if (clientContactsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientContactsList);
        }
    }
//Active/nonactive
    @PutMapping("update-client-contact/{contactId}/active")
    public ResponseEntity<ContactClient> updateContactActiveStatus(@PathVariable Long contactId,@RequestParam boolean active)
    {
        ContactClient updatedContact = contactClientService.updateContactActiveStatus(contactId, active);
        return ResponseEntity.ok(updatedContact);
    }

    @GetMapping("/Total-Contacts")
    public int countTotalContacts() {
        return contactClientService.countTotalContacts();
    }

    @GetMapping("/Total-Active-Contacts")
    public int countActiveContacts() {
        return contactClientService.countActiveContacts();
    }

    @GetMapping("/Total-Inactive-Contacts")
    public int countInActiveContacts() {
        return contactClientService.countInactiveContacts();
    }
}
