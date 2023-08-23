package projet.pfe.tms.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.pfe.tms.dto.FolderInvoicesDTO;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.FolderInvoices;
import projet.pfe.tms.models.Ngp;
import projet.pfe.tms.services.FolderInvoicesService;

import java.util.List;

@RestController
@RequestMapping("/folders/{folderId}/FolderInvoices")
public class FolderInvoicesController {
    private final FolderInvoicesService finvoiceService;


    public FolderInvoicesController(FolderInvoicesService finvoiceService) {
        this.finvoiceService = finvoiceService;
    }

    @PostMapping("/add-FolderInvoice")
    public FolderInvoices addNewFolderInvoice(@PathVariable Long folderId,@RequestBody FolderInvoicesDTO finvoiceDTO){

        return  this.finvoiceService.createFinvoice(folderId,finvoiceDTO);
    }
    @PutMapping("/update-FolderInvoice/{id}")
    public FolderInvoices updateFolderInvoice(@PathVariable Long folderId,@PathVariable Long id, @RequestBody FolderInvoicesDTO finvoiceDTO ){
        return  this.finvoiceService.updateFinvoice(folderId,id, finvoiceDTO);
    }
    @GetMapping("/getAllFolderInvoicesByFolderId")
    public List<FolderInvoices> getAllFolderInvoicesbyFolderId(@PathVariable Long folderId) {
        return this.finvoiceService.getAllFolderInvoicesByFolderId(folderId);
    }

    @GetMapping("/")
    public List<FolderInvoices> getAllFolderInvoice(){
        return this.finvoiceService.listFinvoice();
    }

    @DeleteMapping("/delete-FolderInvoice/{id}")
    public ResponseEntity<String> deleteFolderInvoice(@PathVariable Long id){
        this.finvoiceService.deleteFinvoice(id);
        return ResponseEntity.ok("FolderInvoice a été supprimé avec succès");
    }

    @GetMapping("/{id}")
    public FolderInvoices getFolderInvoiceById(@PathVariable Long id){
        return this.finvoiceService.findFolderInvoicesbyid(id);
    }
}
