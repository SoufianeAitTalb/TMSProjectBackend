package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.LiquidationDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.FolderLiquidation;
import projet.pfe.tms.repositories.FolderLiquidationRepo;
import projet.pfe.tms.services.FolderLiquidationService;
import projet.pfe.tms.services.FolderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FolderLiquidationServiceImp implements FolderLiquidationService {

    private final FolderLiquidationRepo folderLiquidationRepo;
    private final FolderService folderService;

    public FolderLiquidationServiceImp(FolderLiquidationRepo folderLiquidationRepo, FolderService folderService) {
        this.folderLiquidationRepo = folderLiquidationRepo;
        this.folderService = folderService;
    }

    @Override
    public FolderLiquidation createFolderLiquidation(LiquidationDTO liquidationDTO, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        if(folder != null){
            FolderLiquidation folderLiquidation = new FolderLiquidation();

            folderLiquidation.setLiquidationType(liquidationDTO.getLiquidationType());
            folderLiquidation.setAdmisConforme(liquidationDTO.getAdmisConforme());
            folderLiquidation.setCommis(liquidationDTO.getCommis());
            folderLiquidation.setInspecteur(liquidationDTO.getInspecteur());

            if(liquidationDTO.isMainLeve()) {
                folderLiquidation.setMainLeve(true);
                folderLiquidation.setDateMainLeve(LocalDateTime.now());
            }

            if(liquidationDTO.isVisiteDouaniere()){
                folderLiquidation.setVisiteDouaniere(true);
                folderLiquidation.setDateProgrammationVisite(LocalDateTime.now());
            }

            if(liquidationDTO.isBureauValeur()){
                folderLiquidation.setBureauValeur(true);
                folderLiquidation.setDateProgrammationBureau(LocalDateTime.now());
            }

            if(liquidationDTO.getOnssa().equals("admis conforme")){
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
                folderLiquidation.setDateProgrammationOnssa(liquidationDTO.getDateProgrammationOnssa());
            } else if(liquidationDTO.getOnssa().equals("visite douaniere")){
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
                folderLiquidation.setDateVisiteOnssa(liquidationDTO.getDateVisiteOnssa());
            }else {
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
            }

            if(liquidationDTO.getMcia().equals("admis conforme")){
                folderLiquidation.setMcia(liquidationDTO.getMcia());
                folderLiquidation.setDateProgrammationMcia(liquidationDTO.getDateProgrammationMcia());
            } else if(liquidationDTO.getMcia().equals("visite douaniere")){
                folderLiquidation.setMcia(liquidationDTO.getMcia());
                folderLiquidation.setDateVisiteMcia(liquidationDTO.getDateVisiteMcia());
            }else {
                folderLiquidation.setMcia(liquidationDTO.getMcia());
            }

            folderLiquidation.setApurement(liquidationDTO.getApurement());
            folderLiquidation.setDateBon(liquidationDTO.getDateBon());
            folderLiquidation.setDateRemiseBon(liquidationDTO.getDateRemiseBon());

            folderLiquidation.setFolder(folder);

            return this.folderLiquidationRepo.save(folderLiquidation);
        }
        return null;
    }

    @Override
    public FolderLiquidation updateFolderLiquidation(LiquidationDTO liquidationDTO, Long liquidationID, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);

        if(folder != null){
            FolderLiquidation folderLiquidation = this.getLiquidationById(liquidationID);

            folderLiquidation.setLiquidationType(liquidationDTO.getLiquidationType());
            folderLiquidation.setAdmisConforme(liquidationDTO.getAdmisConforme());
            folderLiquidation.setCommis(liquidationDTO.getCommis());
            folderLiquidation.setInspecteur(liquidationDTO.getInspecteur());

            if(liquidationDTO.isMainLeve()) {
                folderLiquidation.setMainLeve(true);
                folderLiquidation.setDateMainLeve(LocalDateTime.now());
            }else{
                folderLiquidation.setDateMainLeve(null);
            }

            if(liquidationDTO.isVisiteDouaniere()){
                folderLiquidation.setVisiteDouaniere(true);
                folderLiquidation.setDateProgrammationVisite(LocalDateTime.now());
            }else{
                folderLiquidation.setDateProgrammationVisite(null);
            }

            if(liquidationDTO.isBureauValeur()){
                folderLiquidation.setBureauValeur(true);
                folderLiquidation.setDateProgrammationBureau(LocalDateTime.now());
            }else{
                folderLiquidation.setDateProgrammationBureau(null);
            }

            if(liquidationDTO.getOnssa().equals("admis conforme")){
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
                folderLiquidation.setDateProgrammationOnssa(liquidationDTO.getDateProgrammationOnssa());
            } else if(liquidationDTO.getOnssa().equals("visite douaniere")){
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
                folderLiquidation.setDateVisiteOnssa(liquidationDTO.getDateVisiteOnssa());
            }else {
                folderLiquidation.setOnssa(liquidationDTO.getOnssa());
            }

            if(liquidationDTO.getMcia().equals("admis conforme")){
                folderLiquidation.setMcia(liquidationDTO.getMcia());
                folderLiquidation.setDateProgrammationMcia(liquidationDTO.getDateProgrammationMcia());
            } else if(liquidationDTO.getMcia().equals("visite douaniere")){
                folderLiquidation.setMcia(liquidationDTO.getMcia());
                folderLiquidation.setDateVisiteMcia(liquidationDTO.getDateVisiteMcia());
            }else {
                folderLiquidation.setMcia(liquidationDTO.getMcia());
            }

            folderLiquidation.setApurement(liquidationDTO.getApurement());
            folderLiquidation.setDateBon(liquidationDTO.getDateBon());
            folderLiquidation.setDateRemiseBon(liquidationDTO.getDateRemiseBon());

            folderLiquidation.setFolder(folder);

            return this.folderLiquidationRepo.save(folderLiquidation);
        }
        return null;
    }

    @Override
    public void deleteFolderLiquidation(Long liquidationID) {
        this.folderLiquidationRepo.deleteById(liquidationID);
    }

    @Override
    public FolderLiquidation getLiquidationByFolderId(Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        return folder.getFolderLiquidation();
    }

    @Override
    public FolderLiquidation getLiquidationById(Long id) {
        return this.folderLiquidationRepo.findById(id).orElse(null);
    }
}
