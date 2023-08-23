package projet.pfe.tms.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.EngagementImportationDTO;
import projet.pfe.tms.dto.FolderInvoicesDTO;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.FolderInvoices;
import projet.pfe.tms.services.EngagementImportationService;
import projet.pfe.tms.services.FolderInvoicesService;

import java.util.List;

@RestController
@RequestMapping("/folders/{folderId}/EngagementImportation")
public class EngagementImportationController {
    private  final EngagementImportationService  engagementImportationService;

    public EngagementImportationController(EngagementImportationService engagementImportationService) {
        this.engagementImportationService = engagementImportationService;
    }

    @PostMapping("/add-EngagementImportation")
    public EngagementImportation addNewEngagementImportation(@PathVariable Long folderId,@RequestBody EngagementImportationDTO engagementImportationDTO){
     return  this.engagementImportationService.createEngagementImportation(folderId,engagementImportationDTO);
    }
    @PutMapping("/update-EngagementImportation/{id}")
    public EngagementImportation updateEngagementImportation(@PathVariable Long folderId,@PathVariable Long id, @RequestBody EngagementImportationDTO engagementImportationDTO ){
        return this.engagementImportationService.updateEngagementImportation(folderId,id,engagementImportationDTO);
    }
    @GetMapping("/getAllEngagementImportationByFolderId")
    public List<EngagementImportation> getAllEngagementImportation(@PathVariable Long folderId) {
        return this.engagementImportationService.getAllEngagementImportationByFolderId(folderId);
    }

    @GetMapping("/")
    public List<EngagementImportation> getAllEngagementImportation(){
        return this.engagementImportationService.listEngagementImportations();
    }

    @DeleteMapping("/delete-EngagementImportation/{id}")
    public ResponseEntity<String> deleteEngagementImportation(@PathVariable Long id){
        this.engagementImportationService.deleteEngagementImportation(id);
        return ResponseEntity.ok("FolderInvoice a été supprimé avec succès");
    }

    @GetMapping("/{id}")
    public EngagementImportation getEngagementImportationById(@PathVariable Long id){
        return this.engagementImportationService.findEngagementImportationbyid(id) ;
    }


}
