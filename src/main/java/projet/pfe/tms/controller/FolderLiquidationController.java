package projet.pfe.tms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.LiquidationDTO;
import projet.pfe.tms.models.FolderLiquidation;
import projet.pfe.tms.services.FolderLiquidationService;

@RestController
@RequestMapping("api/folders/{folderId}/liquidation")
public class FolderLiquidationController {

    private final FolderLiquidationService folderLiquidationService;

    public FolderLiquidationController(FolderLiquidationService folderLiquidationService) {
        this.folderLiquidationService = folderLiquidationService;
    }

    @GetMapping
    public FolderLiquidation getLiquidationByFolderId(@PathVariable Long folderId){
        return this.folderLiquidationService.getLiquidationByFolderId(folderId);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createLiquidation(@PathVariable Long folderId, LiquidationDTO liquidationDTO){

        if(this.folderLiquidationService.createFolderLiquidation(liquidationDTO, folderId) != null){
            return ResponseEntity.ok("La liquidation a été ajoutée avec succès");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout de la liquidation");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLiquidation(@PathVariable Long folderId,
                                                    @PathVariable Long id,
                                                    LiquidationDTO liquidationDTO){

        if(this.folderLiquidationService.updateFolderLiquidation(liquidationDTO, id,folderId) != null){
            return ResponseEntity.ok("La liquidation a été modifiée avec succès");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification de la liquidation");
    }

}
