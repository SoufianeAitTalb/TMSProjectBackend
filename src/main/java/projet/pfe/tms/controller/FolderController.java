package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.FolderDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.services.ClientService;
import projet.pfe.tms.services.FolderService;

import java.util.List;

@RestController
@RequestMapping("api/folders")
public class FolderController {

    private final FolderService folderService;
    private final FolderRepo folderRepo;
    private final ClientService clientService;
    @Autowired
    public FolderController(FolderService folderService, FolderRepo folderRepo, ClientService clientService) {

        this.folderService = folderService;
        this.folderRepo = folderRepo;
        this.clientService = clientService;
    }

    @PostMapping("/add-folder")
    public ResponseEntity<String> createFolder(@RequestBody FolderDTO folderDTO) {
        if (this.folderService.createFolder(folderDTO) != null)
            return ResponseEntity.ok("Le dossier a été crée avec succés");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors du création de dossier");
    }

    @PutMapping("/update-folder/{id}")
    public ResponseEntity<String> updateFolder(@RequestBody FolderDTO folderDTO, @PathVariable Long id) {
        if (this.folderService.updateFolder(folderDTO, id) != null)
            return ResponseEntity.ok("Le dossier a été modifié avec succés");
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification du dossier");
    }

    @DeleteMapping("/delete-folder/{id}")
    public ResponseEntity<String> deleteFolder(@PathVariable Long id) {
        this.folderService.deleteFolder(id);
        return ResponseEntity.ok("Le dossier a été supprimé avec succés");
    }

    @GetMapping("/")
    public List<FolderDTO> getAllFolders() {
        return this.folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    public FolderDTO getFolderById(@PathVariable Long id) {
        return this.folderService.loadFolderByFolderId(id);
    }

    @GetMapping("/{folderId}/clientCompany")
    public String getClientCompany(@PathVariable Long folderId) {
        Folder folder = folderRepo.findById(folderId).orElse(null);
        if (folder != null) {
            Client client = clientService.loadClientById(folder.getClient().getClientId());
            if (client != null) {
                return client.getCompany();
            }
        }
        return null;
    }
}