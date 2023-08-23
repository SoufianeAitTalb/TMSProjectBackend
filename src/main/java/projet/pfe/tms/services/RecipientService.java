package projet.pfe.tms.services;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.RecipientDTO;
import projet.pfe.tms.models.Recipient;

import java.util.List;

@Service
public interface RecipientService {

    List<Recipient> getAllRecipients();
    Recipient getRecipientById(Long id);
    Recipient addRecipient(RecipientDTO recipientDTO);
    Recipient updateRecipient(Long id, RecipientDTO recipientDTO);
    void deleteRecipient(Long id);

}
