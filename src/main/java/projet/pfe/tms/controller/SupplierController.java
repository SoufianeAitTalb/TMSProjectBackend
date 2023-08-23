package projet.pfe.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import projet.pfe.tms.dto.SupplierDTO;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.services.SupplierService;
import projet.pfe.tms.services.impl.SupplierServiceImpl;

@EnableWebMvc
@RestController
@RequestMapping("api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierServiceImpl supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public List<Supplier> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @GetMapping("/{id}")
    // @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplier = supplierService.loadSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(supplier);
        }

    }

    @PostMapping("/add-supplier")
//    @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addSupplier(@RequestBody SupplierDTO supplierDto) {



        if (supplierService.addSupplier(supplierDto) != null)
            return ResponseEntity.ok("L'ajout du fournisseur a ete reussi");

            return ResponseEntity.badRequest().body("Echec de l'enregistrement du fournisseur");

    }

    @PutMapping("/update-supplier/{id}")
  //  @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDto) {

        if (supplierService.updateSupplier(id, supplierDto) != null)
            return ResponseEntity.ok("La modification du fournisseur a ete reussie");

        return ResponseEntity.badRequest().body("Echec de la modification du fournisseur");
    }

    @DeleteMapping("/delete-supplier/{id}")
   // @PostAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("La suppression du fournisseur a ete reussie");
    }
    
    @PutMapping("/{id}/active")
 // @PostAuthorize("hasAuthority('ADMIN')")
 public ResponseEntity<String> updateSupplierActiveStatus(@PathVariable Long id, @RequestParam boolean active) {
     Supplier supplier = supplierService.updateSupplierActiveStatus(id, active);
     if (supplier != null) {
         return ResponseEntity.ok("Le statut de fournisseur a été mis à jour avec succès");
     } else {
         return ResponseEntity.badRequest().body("Echec de la mise à jour du statut de fournisseur");
     }
 }

    @GetMapping("/count-total")
    public int countTotalSuppliers() {
        return supplierService.countTotalSuppliers();
    }

    @GetMapping("/count-active")
    public int countActiveSuppliers() {
        return supplierService.countActiveSuppliers();
    }

    @GetMapping("/count-inactive")
    public int countInactiveSuppliers() {
        return supplierService.countInactiveSuppliers();
    }
}
