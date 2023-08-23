package projet.pfe.tms.services;

import projet.pfe.tms.dto.TypeLiquidationDTO;
import projet.pfe.tms.models.TypeLiquidation;

import java.util.List;

public interface TypeLiquidationService {

    TypeLiquidation createTypeLiquidation(TypeLiquidationDTO typeLiquidationDTO, Long folderID);
    TypeLiquidation updateTypeLiquidation(TypeLiquidationDTO typeLiquidationDTO, Long typeLiquidationID,Long folderID);
    void deleteTypeLiquidation(Long id);
    List<TypeLiquidation> getTypesLiquidationByFolderId(Long folderID);
    TypeLiquidation getTypeLiquidationById(Long id);

}
