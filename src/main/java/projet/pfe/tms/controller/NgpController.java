package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.pfe.tms.dto.NgpDTO;
import projet.pfe.tms.models.Ngp;
import projet.pfe.tms.services.NgpService;

import java.util.List;


@RestController
@RequestMapping("api/Ngps")

public class NgpController {
   private final NgpService ngpService;

   @Autowired
   public NgpController(NgpService ngpService){
       this.ngpService = ngpService;
   }


  @PostMapping("/add-Ngp")
   public ResponseEntity<String> addNewNgp(@RequestBody NgpDTO ngpDTO){


       if (this.ngpService.loadNgpBycodeProduct(ngpDTO.getCodeProduct()) != null)
           return ResponseEntity.badRequest().body("Ce CodeProduct deha exist ");

       if(this.ngpService.createNgp(ngpDTO) != null)
           return ResponseEntity.ok(" Module Ngp a été ajouté avec succès");

       return ResponseEntity.badRequest().body("Une erreur lors de l'ajout du Module ngp");
   }
    @PutMapping("/update-Ngp/{id}")
    public ResponseEntity<String> updateNgp(@PathVariable Long id, @RequestBody NgpDTO ngpDTO ){

        if(this.ngpService.updateNgp(id, ngpDTO) != null )
            return ResponseEntity.ok("Ngp  a été modifié avec succès");

        return ResponseEntity.badRequest().body("Une erreur  lors de la modification du ngp");
    }
    @GetMapping("/{id}")
    public Ngp getNgpById(@PathVariable Long id){
        return this.ngpService.getNgpById(id);
    }
    @GetMapping("/")
    public List<Ngp> getAllNps(){
        return this.ngpService.listNgp();
    }

   @DeleteMapping("/delete-ngp/{id}")
   public ResponseEntity<String> deleteNgp(@PathVariable Long id){
       this.ngpService.deleteNgp(id);
       return ResponseEntity.ok("ngp  a été supprimé avec succès");
   }
}