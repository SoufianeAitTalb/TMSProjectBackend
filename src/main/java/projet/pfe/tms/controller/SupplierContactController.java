package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.SupplierContactDTO;
import projet.pfe.tms.models.SupplierContact;
import projet.pfe.tms.services.SupplierContactService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier-contacts")
public class SupplierContactController {

    @Autowired
    private SupplierContactService supplierContactService;

    @GetMapping("/")
    public List<SupplierContact> listSupplierContacts() {

        return supplierContactService.loadAllSupplierContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierContactById(@PathVariable Long id) {
        SupplierContact supplierContact = supplierContactService.loadSupplierContactById(id);
        if (supplierContact == null) {
            return ResponseEntity.badRequest().body("Contact Not found ");
        } else {
            return ResponseEntity.ok(supplierContact);
        }
    }

    @PostMapping("/add-supplier-contact")
    public ResponseEntity<String> addSupplierContact(@RequestBody SupplierContactDTO supplierContactDTO) {
        if (supplierContactService.addSupplierContact(supplierContactDTO) != null) {
            return ResponseEntity.ok("Supplier contact added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add supplier contact");
        }
    }

   
    
    @PutMapping("/update-supplier-contact/{supplierId}/{id}")
    public ResponseEntity<String> updateSupplierContact(@PathVariable Long supplierId, @PathVariable Long id, @RequestBody SupplierContactDTO supplierContactDTO) {
        if (supplierContactService.updateSupplierContact(supplierId, id, supplierContactDTO) != null) {
            return ResponseEntity.ok("Supplier contact updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update supplier contact");
        }
    }


    @DeleteMapping("/delete-supplier-contact/{id}")
    public ResponseEntity<String> deleteSupplierContact(@PathVariable Long id) {
        supplierContactService.deleteSupplierContact(id);
        return ResponseEntity.ok("Supplier contact deleted successfully");
    }
    @GetMapping("/supplier/{id}/contacts")
    public ResponseEntity<List<SupplierContact>> getAllContactsBySupplier(@PathVariable Long id) {
        List<SupplierContact> supplierContactsList = supplierContactService.getAllContactBySupplier(id);
        if (supplierContactsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(supplierContactsList);
        }
    }
    
    @PostMapping("/supplier/{id}/contacts")
    public ResponseEntity<String> addSupplierContactBySupplierId(@PathVariable Long id, @RequestBody SupplierContactDTO supplierContactDTO) {
        supplierContactDTO.setSupplierId(id);
        if (supplierContactService.addSupplierContact(supplierContactDTO) != null) {
            return ResponseEntity.ok("Supplier contact added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add supplier contact");
        }
    }
    
    @GetMapping("/principal-full-name/{id}")
    public ResponseEntity<String> getFullNameBySupplier(@PathVariable Long id) {
        String fullName = supplierContactService.getPrincipalFullNameBySupplier(id);
        if (fullName == null) {
            return ResponseEntity.badRequest().body("Primary contact not found for supplier " + id);
        } else {
            return ResponseEntity.ok(fullName);
        }
    }
    
    @GetMapping("/principal-email/{id}")
    public ResponseEntity<?> getEmailBySupplier(@PathVariable Long id) {
        String email = supplierContactService.getPrincipalContactEmailBySupplier(id);
        if (email == null) {
            return ResponseEntity.badRequest().body("No contact found for supplier " + id);
        } else {
            return ResponseEntity.ok(email);
        }
    }

}
