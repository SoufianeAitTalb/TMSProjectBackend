package projet.pfe.tms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.ServiceLiquidationDTO;
import projet.pfe.tms.models.ServiceLiquidation;
import projet.pfe.tms.services.ServiceLiquidationService;

import java.util.List;

@RestController
@RequestMapping("api/folders/{folderId}/liquidation-services")
public class ServiceLiquidationController {

    private final ServiceLiquidationService serviceLiquidationService;

    public ServiceLiquidationController(ServiceLiquidationService serviceLiquidationService) {
        this.serviceLiquidationService = serviceLiquidationService;
    }

    @GetMapping("/")
    public List<ServiceLiquidation> getAllServicesLiquidation(@PathVariable Long folderId){
        return this.serviceLiquidationService.getAllServicesLiquidationByFolderId(folderId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createServiceLiquidation(@RequestBody ServiceLiquidationDTO serviceLiquidationDTO,
                                                           @PathVariable Long folderId){
        if(this.serviceLiquidationService.createServiceLiquidation(serviceLiquidationDTO, folderId) != null){
            return ResponseEntity.ok("Le service de liquidation a été ajouté avec succés");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de l'ajout du service de liquidation");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateServiceLiquidation(@PathVariable Long folderId,
                                                    @PathVariable Long id,
                                                    ServiceLiquidationDTO serviceLiquidationDTO){

        if(this.serviceLiquidationService.updateServiceLiquidation(serviceLiquidationDTO, id, folderId) != null){
            return ResponseEntity.ok("Le service de liquidation a été modifié avec succés");
        }
        return ResponseEntity.badRequest().body("Une erreur s'est produite lors de la modification " +
                "du service de liquidation");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteServiceLiquidation(@PathVariable Long id){
        this.serviceLiquidationService.deleteServiceLiquidation(id);
        return ResponseEntity.ok("Le service de liquidation a été supprimé avec succés");
    }

}
