package projet.pfe.tms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import projet.pfe.tms.dto.RecipientDTO;
import projet.pfe.tms.models.Recipient;
import projet.pfe.tms.services.RecipientService;

import java.util.List;

@RestController
@RequestMapping("/api/recipients")
@EnableWebMvc
public class RecipientController {


    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @GetMapping("/")
    public List<Recipient> getAllRecipients() {

        return recipientService.getAllRecipients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipient> getRecipientById(@PathVariable Long id) {
        Recipient recipient = recipientService.getRecipientById(id);
        if (recipient == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(recipient);
        }
    }

    @PostMapping("/add-recipient")
    public ResponseEntity<String> addRecipient(@RequestBody RecipientDTO recipientDTO) {

        if (recipientService.addRecipient(recipientDTO) !=null)
            return ResponseEntity.ok("L'ajout du destinataire a ete reussi");

        return ResponseEntity.badRequest().body("Echec de l'enregistrement du destinataire");
    }

    @PutMapping("/update-recipient/{id}")
    public ResponseEntity<String> updateRecipient(@PathVariable Long id, @RequestBody RecipientDTO recipientDTO) {
        if (recipientService.updateRecipient(id, recipientDTO) != null) {
            return ResponseEntity.ok("La modification de destinataire a ete reussi");
        } else {
            return ResponseEntity.badRequest().body("Echec de la modification du destinataire");
        }
    }
    @DeleteMapping("/delete-recipient/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        recipientService.deleteRecipient(id);
        return ResponseEntity.ok("La suppression du destinataire a ete reussie");
    }

}

