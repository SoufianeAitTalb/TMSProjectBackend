package projet.pfe.tms.services;

import projet.pfe.tms.dto.EngagementImportationDTO;
import projet.pfe.tms.dto.FolderInvoicesDTO;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.FolderInvoices;

import java.util.List;

public interface EngagementImportationService {
    EngagementImportation createEngagementImportation(Long folderId,EngagementImportationDTO engagementImportation);
    List<EngagementImportation> listEngagementImportations();
    EngagementImportation updateEngagementImportation(Long folderId,Long id, EngagementImportationDTO engagementImportationDTO );
    void deleteEngagementImportation(Long id);
    EngagementImportation  findEngagementImportationbyid(Long id);

   List<EngagementImportation> getAllEngagementImportationByFolderId(Long folderId);
}
