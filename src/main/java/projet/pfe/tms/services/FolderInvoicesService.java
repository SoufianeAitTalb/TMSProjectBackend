package projet.pfe.tms.services;

import projet.pfe.tms.dto.FolderInvoicesDTO;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.FolderInvoices;

import java.util.List;

public interface FolderInvoicesService {
    FolderInvoices createFinvoice(Long folderId,FolderInvoicesDTO finvoiceDTO);
    List<FolderInvoices> listFinvoice();
    FolderInvoices updateFinvoice(Long folderId,Long id, FolderInvoicesDTO finvoiceDTO );
    void deleteFinvoice(Long id);
    FolderInvoices  findFolderInvoicesbyid(Long id);
    List<FolderInvoices> getAllFolderInvoicesByFolderId(Long folderId);

}
