package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Repository;
import projet.pfe.tms.dto.EngagementImportationDTO;
import projet.pfe.tms.models.EngagementImportation;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.FolderInvoices;
import projet.pfe.tms.repositories.EngagementImportationRepo;
import projet.pfe.tms.services.EngagementImportationService;
import projet.pfe.tms.services.FolderService;

import java.util.List;
@Repository
public class EngagementImportationServiceImpl implements EngagementImportationService {

    private final EngagementImportationRepo engagementImportationRepo;

    private final FolderService folderService;

    public EngagementImportationServiceImpl(EngagementImportationRepo engagementImportationRepo, FolderService folderService) {
        this.engagementImportationRepo = engagementImportationRepo;

        this.folderService = folderService;
    }


    @Override
    public EngagementImportation createEngagementImportation(Long folderId,EngagementImportationDTO engagementImportationDTO) {
        Folder folder = this.folderService.loadFolderById(folderId);;
        EngagementImportation engagementImportation = new  EngagementImportation();
        engagementImportation.setFolder(folder);
        engagementImportation.setAutreDevise(engagementImportationDTO.getAutreDevise());
        engagementImportation.setBanqueEng(engagementImportationDTO.getBanqueEng());
        engagementImportation.setDateDomicialisationEn(engagementImportationDTO.getDateDomicialisationEn());
        engagementImportation.setEffectuePar(engagementImportationDTO.getEffectuePar());
        engagementImportation.setNumEng(engagementImportationDTO.getNumEng());
        engagementImportation.setPoidnEng(engagementImportationDTO.getPoidnEng());
        engagementImportation.setValeurTotaleEng(engagementImportationDTO.getValeurTotaleEng());
        engagementImportation.setDateEcheanceEn(engagementImportationDTO.getDateEcheanceEn());
        engagementImportation.setNumFactureEng(engagementImportationDTO.getNumFactureEng());


        return engagementImportationRepo.save(engagementImportation);
    }


    @Override
    public EngagementImportation updateEngagementImportation(Long folderId,Long id, EngagementImportationDTO engagementImportationDTO) {

        EngagementImportation engagementImportation  = engagementImportationRepo.findById(id).orElse(null);
        // .orElseThrow(() -> new EntityNotFoundException("Not found"));
        if(engagementImportation == null) {
            Folder folder = this.folderService.loadFolderById(folderId);
            EngagementImportation newEngagementImportation  = new  EngagementImportation();
            newEngagementImportation.setFolder(folder);
            newEngagementImportation.setAutreDevise(engagementImportationDTO.getAutreDevise());
            newEngagementImportation.setBanqueEng(engagementImportationDTO.getBanqueEng());
            newEngagementImportation.setDateDomicialisationEn(engagementImportationDTO.getDateDomicialisationEn());
            newEngagementImportation.setEffectuePar(engagementImportationDTO.getEffectuePar());
            newEngagementImportation.setNumEng(engagementImportationDTO.getNumEng());
            newEngagementImportation.setPoidnEng(engagementImportationDTO.getPoidnEng());
            newEngagementImportation.setValeurTotaleEng(engagementImportationDTO.getValeurTotaleEng());
            newEngagementImportation.setDateEcheanceEn(engagementImportationDTO.getDateEcheanceEn());
            newEngagementImportation.setNumFactureEng(engagementImportationDTO.getNumFactureEng());
            return engagementImportationRepo.save(newEngagementImportation);
        }
        else {
            engagementImportation.setAutreDevise(engagementImportationDTO.getAutreDevise());
            engagementImportation.setBanqueEng(engagementImportationDTO.getBanqueEng());
            engagementImportation.setDateDomicialisationEn(engagementImportationDTO.getDateDomicialisationEn());
            engagementImportation.setEffectuePar(engagementImportationDTO.getEffectuePar());
            engagementImportation.setNumEng(engagementImportationDTO.getNumEng());
            engagementImportation.setPoidnEng(engagementImportationDTO.getPoidnEng());
            engagementImportation.setValeurTotaleEng(engagementImportationDTO.getValeurTotaleEng());
            engagementImportation.setDateEcheanceEn(engagementImportationDTO.getDateEcheanceEn());
            engagementImportation.setNumFactureEng(engagementImportationDTO.getNumFactureEng());
            return engagementImportationRepo.save(engagementImportation);
            }
    }

    @Override
    public List<EngagementImportation> listEngagementImportations() {
        return engagementImportationRepo.findAll();
    }

    @Override
    public void deleteEngagementImportation(Long id) {
        engagementImportationRepo.deleteById(id);

    }

    @Override
    public EngagementImportation findEngagementImportationbyid(Long id) {
        return this.engagementImportationRepo.findById(id).orElse(null);
    }

   @Override
    public List<EngagementImportation> getAllEngagementImportationByFolderId(Long folderId) {
        return engagementImportationRepo.findByFolder_FolderId(folderId);
    }

}
