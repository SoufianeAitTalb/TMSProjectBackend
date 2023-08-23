package projet.pfe.tms.services;


import projet.pfe.tms.dto.ServiceLiquidationDTO;
import projet.pfe.tms.models.ServiceLiquidation;

import java.util.List;

public interface ServiceLiquidationService {

    ServiceLiquidation createServiceLiquidation(ServiceLiquidationDTO serviceDTO, Long folderID);
    ServiceLiquidation updateServiceLiquidation(ServiceLiquidationDTO serviceDTO, Long ServiceID, Long folderID);
    void deleteServiceLiquidation(Long id);
    List<ServiceLiquidation> getAllServicesLiquidationByFolderId(Long folderID);
    ServiceLiquidation getServiceLiquidationById(Long id);
}
