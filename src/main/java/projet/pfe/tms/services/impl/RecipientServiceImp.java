package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.RecipientDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.Recipient;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.repositories.RecipientRepo;
import projet.pfe.tms.services.RecipientService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class RecipientServiceImp implements RecipientService {
    private final RecipientRepo recipientRepo;
    private  final FolderRepo folderRepo;
    public RecipientServiceImp(RecipientRepo recipientRepo, FolderRepo folderRepo) {
        this.recipientRepo = recipientRepo;
        this.folderRepo = folderRepo;
    }


    @Override
    public List<Recipient> getAllRecipients() {
        return recipientRepo.findAll();
    }

    @Override
    public Recipient getRecipientById(Long id) {
        return recipientRepo.findById(id)
                .orElse(null);
    }

    @Override
    public Recipient addRecipient(RecipientDTO recipientDTO) {

        Recipient recipient=new Recipient();
        recipient.setAddress(recipientDTO.getAddress());
        recipient.setName(recipientDTO.getName());
        return recipientRepo.save(recipient);
    }

    @Override
    public Recipient updateRecipient(Long id, RecipientDTO recipientDTO) {

        Recipient recipient = recipientRepo.findById(id).orElse(null);
        recipient.setAddress(recipientDTO.getAddress());
        recipient.setName(recipientDTO.getName());
        return recipientRepo.save(recipient);
    }

    @Override
    public void deleteRecipient(Long id) {
     recipientRepo.deleteById(id);
        Recipient recipient = recipientRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));


        List<Folder> folderList = folderRepo.findFolderByRecipient(recipient);
        for (Folder folder : folderList) {
            folder.setRecipient(null);
            folderRepo.save(folder);
        }

        recipientRepo.delete(recipient);
    }
}
