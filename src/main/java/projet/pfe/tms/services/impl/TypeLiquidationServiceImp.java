package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.TypeLiquidationDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.FolderLiquidation;
import projet.pfe.tms.models.TypeLiquidation;
import projet.pfe.tms.repositories.TypeLiquidationRepo;
import projet.pfe.tms.services.FolderLiquidationService;
import projet.pfe.tms.services.FolderService;
import projet.pfe.tms.services.TypeLiquidationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeLiquidationServiceImp implements TypeLiquidationService {

    private final TypeLiquidationRepo typeLiquidationRepo;
    private final FolderService folderService;
    private final FolderLiquidationService folderLiquidationService;

    public TypeLiquidationServiceImp(TypeLiquidationRepo typeLiquidationRepo, FolderService folderService, FolderLiquidationService folderLiquidationService) {
        this.typeLiquidationRepo = typeLiquidationRepo;
        this.folderService = folderService;
        this.folderLiquidationService = folderLiquidationService;
    }

    @Override
    public TypeLiquidation createTypeLiquidation(TypeLiquidationDTO typeLiquidationDTO, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        if(folder != null && folder.getFolderLiquidation() != null){
            TypeLiquidation typeLiquidation = new TypeLiquidation();

            typeLiquidation.setModeLiquidation(typeLiquidationDTO.getModeLiquidation());
            typeLiquidation.setDateEmission(typeLiquidationDTO.getDateEmission());
            typeLiquidation.setDateReception(typeLiquidationDTO.getDateReception());
            typeLiquidation.setNumeroCheque(typeLiquidationDTO.getNumeroCheque());
            typeLiquidation.setQuittance(typeLiquidationDTO.getQuittance());
            typeLiquidation.setNumFl(typeLiquidationDTO.getNumFl());
            typeLiquidation.setDateFl(typeLiquidationDTO.getDateFl());
            typeLiquidation.setMontantTotal(typeLiquidationDTO.getMontantTotal());
            typeLiquidation.setDateEcheance(typeLiquidationDTO.getDateEcheance());
            typeLiquidation.setInteretRetard(typeLiquidationDTO.getInteretRetard());

            typeLiquidation.setFolder(folder);
            typeLiquidation.setFolderLiquidation(folder.getFolderLiquidation());

            return this.typeLiquidationRepo.save(typeLiquidation);
        }
        return null;
    }

    @Override
    public TypeLiquidation updateTypeLiquidation(TypeLiquidationDTO typeLiquidationDTO, Long typeLiquidationID, Long folderID) {
        Folder folder = this.folderService.loadFolderById(folderID);
        if(folder != null && folder.getFolderLiquidation() != null){
            TypeLiquidation typeLiquidation = this.getTypeLiquidationById(typeLiquidationID);

            typeLiquidation.setModeLiquidation(typeLiquidationDTO.getModeLiquidation());
            typeLiquidation.setDateEmission(typeLiquidationDTO.getDateEmission());
            typeLiquidation.setDateReception(typeLiquidationDTO.getDateReception());
            typeLiquidation.setNumeroCheque(typeLiquidationDTO.getNumeroCheque());
            typeLiquidation.setQuittance(typeLiquidationDTO.getQuittance());
            typeLiquidation.setNumFl(typeLiquidationDTO.getNumFl());
            typeLiquidation.setDateFl(typeLiquidationDTO.getDateFl());
            typeLiquidation.setMontantTotal(typeLiquidationDTO.getMontantTotal());
            typeLiquidation.setDateEcheance(typeLiquidationDTO.getDateEcheance());
            typeLiquidation.setInteretRetard(typeLiquidationDTO.getInteretRetard());

            typeLiquidation.setFolder(folder);
            typeLiquidation.setFolderLiquidation(folder.getFolderLiquidation());

            return this.typeLiquidationRepo.save(typeLiquidation);
        }
        return null;
    }

    @Override
    public void deleteTypeLiquidation(Long id) {
        this.typeLiquidationRepo.deleteById(id);
    }

    @Override
    public List<TypeLiquidation> getTypesLiquidationByFolderId(Long folderId) {
        Folder folder = folderService.loadFolderById(folderId);
        List<TypeLiquidation> listTypes = new ArrayList<>();

        if (folder != null && folder.getTypeLiquidation() != null) {
            listTypes.add(folder.getTypeLiquidation());
        }

        return listTypes;
    }

    @Override
    public TypeLiquidation getTypeLiquidationById(Long id) {
        return this.typeLiquidationRepo
                .findById(id)
                .orElse(null);
    }
}
