package projet.pfe.tms.services;

import projet.pfe.tms.dto.LiquidationDTO;
import projet.pfe.tms.models.FolderLiquidation;

import java.util.List;

public interface FolderLiquidationService {

    FolderLiquidation createFolderLiquidation(LiquidationDTO liquidationDTO, Long folderID);
    FolderLiquidation updateFolderLiquidation(LiquidationDTO liquidationDTO, Long liquidationID,Long folderID);
    void deleteFolderLiquidation(Long liquidationID);
    FolderLiquidation getLiquidationByFolderId(Long folderID);
    FolderLiquidation getLiquidationById(Long id);

}
