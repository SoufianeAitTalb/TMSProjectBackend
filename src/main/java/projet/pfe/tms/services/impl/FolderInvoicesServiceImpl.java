package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.FolderInvoicesDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.FolderInvoices;
import projet.pfe.tms.repositories.FolderInvoicesRepo;
import projet.pfe.tms.services.FolderInvoicesService;
import projet.pfe.tms.services.FolderService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FolderInvoicesServiceImpl  implements FolderInvoicesService{

    private final FolderInvoicesRepo finvoiceRepo;

    private FolderService folderService;

    public FolderInvoicesServiceImpl(FolderInvoicesRepo finvoiceRepo, FolderService folderService) {
        this.finvoiceRepo = finvoiceRepo;
        this.folderService=folderService;
    }

    @Override
    public FolderInvoices createFinvoice(Long folderId,FolderInvoicesDTO finvoiceDTO) {
        Folder folder = this.folderService.loadFolderById(folderId);
        FolderInvoices folderInvoice = new  FolderInvoices();
        folderInvoice.setFolder(folder);
        folderInvoice.setDeviseInv(finvoiceDTO.getDeviseInv());
        folderInvoice.setMontantTotal(finvoiceDTO.getMontantTotal());
        folderInvoice.setNumFacture(finvoiceDTO.getNumFacture());
        folderInvoice.setRefClient(finvoiceDTO.getRefClient());


        return finvoiceRepo.save(folderInvoice);
    }

    @Override
    public FolderInvoices updateFinvoice(Long folderId,Long id, FolderInvoicesDTO finvoiceDTO) {
        FolderInvoices folderInvoice = finvoiceRepo.findById(id).orElse(null);
               // .orElseThrow(() -> new EntityNotFoundException("Not found"));
        if(folderInvoice == null)
        {    Folder folder = this.folderService.loadFolderById(folderId);;
            FolderInvoices newFolderInvoice = new FolderInvoices();
            newFolderInvoice.setFolder(folder);
            newFolderInvoice.setDeviseInv(finvoiceDTO.getDeviseInv());
            newFolderInvoice.setMontantTotal(finvoiceDTO.getMontantTotal());
            newFolderInvoice.setNumFacture(finvoiceDTO.getNumFacture());
            newFolderInvoice.setRefClient(finvoiceDTO.getRefClient());
            return finvoiceRepo.save(newFolderInvoice);
        }

        else{
            folderInvoice.setDeviseInv(finvoiceDTO.getDeviseInv());
            folderInvoice.setMontantTotal(finvoiceDTO.getMontantTotal());
            folderInvoice.setNumFacture(finvoiceDTO.getNumFacture());
            folderInvoice.setRefClient(finvoiceDTO.getRefClient());
            return finvoiceRepo.save(folderInvoice);

        }

    }

    @Override
    public List<FolderInvoices> listFinvoice() {
        return  this.finvoiceRepo.findAll();
    }


    @Override
    public void deleteFinvoice(Long id) {
        finvoiceRepo.deleteById(id);

    }

    @Override
    public FolderInvoices findFolderInvoicesbyid(Long id) {
        return this.finvoiceRepo.findById(id).orElse(null);
    }

    @Override
    public List<FolderInvoices> getAllFolderInvoicesByFolderId(Long folderId) {
        return this.finvoiceRepo.findByFolder_FolderId(folderId);
    }
}
