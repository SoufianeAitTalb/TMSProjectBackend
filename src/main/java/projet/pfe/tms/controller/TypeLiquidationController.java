package projet.pfe.tms.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.TypeLiquidationDTO;
import projet.pfe.tms.models.TypeLiquidation;
import projet.pfe.tms.services.TypeLiquidationService;

import java.util.List;

@RestController
@RequestMapping("api/folders/{folderId}/type-liquidation")
public class TypeLiquidationController {

    private final TypeLiquidationService typeLiquidationService;

    public TypeLiquidationController(TypeLiquidationService typeLiquidationService) {
        this.typeLiquidationService = typeLiquidationService;
    }

    @GetMapping("/")
    public List<TypeLiquidation> getTypesLiquidationByFolderId(@PathVariable Long folderId){
        return this.typeLiquidationService.getTypesLiquidationByFolderId(folderId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTypeLiquidation(@PathVariable Long folderId,
                                                        TypeLiquidationDTO typeLiquidationDTO){

        if(this.typeLiquidationService.createTypeLiquidation(typeLiquidationDTO, folderId) != null){
            return ResponseEntity.ok("Le type de liquidation a été ajouté avec succès");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du type de liquidation");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLiquidation(@PathVariable Long folderId,
                                                    @PathVariable Long id,
                                                    TypeLiquidationDTO typeLiquidationDTO){

        if(this.typeLiquidationService.updateTypeLiquidation(typeLiquidationDTO, id, folderId) != null){
            return ResponseEntity.ok("Le type de liquidation a été modifiée avec succès");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification du type de liquidation");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteServiceLiquidation(@PathVariable Long id){
        this.typeLiquidationService.deleteTypeLiquidation(id);
        return ResponseEntity.ok("Le type de liquidation a été supprimé avec succés");
    }

}
