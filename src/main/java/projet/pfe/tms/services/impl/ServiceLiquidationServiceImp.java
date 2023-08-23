package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.ServiceLiquidationDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.ServiceLiquidation;
import projet.pfe.tms.repositories.ServiceLiquidationRepo;
import projet.pfe.tms.services.FolderService;
import projet.pfe.tms.services.ServiceLiquidationService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceLiquidationServiceImp implements ServiceLiquidationService {

    private final ServiceLiquidationRepo serviceLiquidationRepo;
    private final FolderService folderService;

    public ServiceLiquidationServiceImp(ServiceLiquidationRepo serviceLiquidationRepo, FolderService folderService) {
        this.serviceLiquidationRepo = serviceLiquidationRepo;
        this.folderService = folderService;
    }

    @Override
    public ServiceLiquidation createServiceLiquidation(ServiceLiquidationDTO serviceDTO, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        if(folder != null){
            ServiceLiquidation serviceLiquidation = new ServiceLiquidation();
            serviceLiquidation.setServiceName(serviceDTO.getServiceName());
            serviceLiquidation.setConfirme(serviceDTO.getConfirme());

            if(serviceDTO.getConfirme().equals("oui")){
                serviceLiquidation.setDateProgrammation(LocalDateTime.now());
            }else {
                serviceLiquidation.setDateProgrammation(null);
            }

            serviceLiquidation.setDateDemandeService(serviceDTO.getDateDemandeService());
            serviceLiquidation.setDateValidation(serviceDTO.getDateValidation());
            serviceLiquidation.setFolder(folder);

            return this.serviceLiquidationRepo.save(serviceLiquidation);
        }
        return null;
    }

    @Override
    public ServiceLiquidation updateServiceLiquidation(ServiceLiquidationDTO serviceDTO, Long ServiceID, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        if(folder != null){
            ServiceLiquidation serviceLiquidation = this.getServiceLiquidationById(ServiceID);

            serviceLiquidation.setServiceName(serviceDTO.getServiceName());

            serviceLiquidation.setConfirme(serviceDTO.getConfirme());
            if(serviceDTO.getConfirme().equals("oui")){
                serviceLiquidation.setDateProgrammation(LocalDateTime.now());
            }else {
                serviceLiquidation.setDateProgrammation(null);
            }

            serviceLiquidation.setDateDemandeService(serviceDTO.getDateDemandeService());
            serviceLiquidation.setDateValidation(serviceDTO.getDateValidation());
            serviceLiquidation.setFolder(folder);

            return this.serviceLiquidationRepo.save(serviceLiquidation);
        }
        return null;
    }

    @Override
    public void deleteServiceLiquidation(Long id) {
        this.serviceLiquidationRepo.deleteById(id);
    }

    @Override
    public List<ServiceLiquidation> getAllServicesLiquidationByFolderId(Long folderID) {
        Folder folder = folderService.loadFolderById(folderID);
        return folder != null ? folder.getServicesLiquidationList() : Collections.emptyList();
    }

    @Override
    public ServiceLiquidation getServiceLiquidationById(Long id) {
        return this.serviceLiquidationRepo
                .findById(id)
                .orElse(null);
    }

}
