package projet.pfe.tms.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.MarchandiseDTO;
import projet.pfe.tms.models.Agrement;
import projet.pfe.tms.models.Echange;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.Marchandise;
import projet.pfe.tms.repositories.MarchandiseRepo;
import projet.pfe.tms.services.AgrementService;
import projet.pfe.tms.services.FolderService;
import projet.pfe.tms.services.MarchandiseService;
@Service
public class MarchandiseServiceImp implements MarchandiseService {

    @Autowired
    private MarchandiseRepo marchandiseRepository;

    @Autowired
    private FolderService folderService;

    @Autowired
    private AgrementService agrementService;

    @Override
    public Marchandise addMarchandise(Long folderId, MarchandiseDTO marchandiseDTO) {
        Folder folder = folderService.getFolderById(folderId);
        Agrement agrement = agrementService.getAgrementById(marchandiseDTO.getAgrementId());

        Marchandise marchandise = new Marchandise();
        marchandise.setNature(marchandiseDTO.getNature());
        marchandise.setNombre(marchandiseDTO.getNombre());
        marchandise.setMarque(marchandiseDTO.getMarque());
        marchandise.setTransType(marchandiseDTO.getTransType());
        marchandise.setDescription(marchandiseDTO.getDescription());
        marchandise.setPaysProvenance(marchandiseDTO.getPaysProvenance());
        marchandise.setIncoterms(marchandiseDTO.getIncoterms());
        marchandise.setTypeCont(marchandiseDTO.getTypeCont());
        marchandise.setNumCont(marchandiseDTO.getNumCont());
        marchandise.setPoidb(marchandiseDTO.getPoidb());
        marchandise.setPoidn(marchandiseDTO.getPoidn());
        marchandise.setStatusMarchandise(marchandiseDTO.getStatusMarchandise());
        marchandise.setVolume(marchandiseDTO.getVolume());
        marchandise.setFranchise(marchandiseDTO.getFranchise());
        marchandise.setTypeRetard(marchandiseDTO.getTypeRetard());
        marchandise.setCommentaire(marchandiseDTO.getCommentaire());
        marchandise.setConComp(marchandiseDTO.getConComp());
        marchandise.setConBase(marchandiseDTO.getConBase());
        marchandise.setCompagnie(marchandiseDTO.getCompagnie());
        marchandise.setNVoyage(marchandiseDTO.getNVoyage());
        marchandise.setDateRec(marchandiseDTO.getDateRec());
        marchandise.setNavire(marchandiseDTO.getNavire());
        marchandise.setDatePrev(marchandiseDTO.getDatePrev());
        marchandise.setChargement(marchandiseDTO.getChargement());
        marchandise.setHawb(marchandiseDTO.getHawb());
        marchandise.setVol(marchandiseDTO.getVol());
        marchandise.setLtaN(marchandiseDTO.getLtaN());
        marchandise.setMagasin(marchandiseDTO.getMagasin());
        marchandise.setDechargement(marchandiseDTO.getDechargement());
        marchandise.setFolder(folder);
        marchandise.setAgrement(agrement);

        return marchandiseRepository.save(marchandise);
    }
    
    @Override
    public Marchandise updateMarchandise(Long folderId,Long MarchandiseId, MarchandiseDTO marchandiseDTO) {
    	Marchandise marchandise = marchandiseRepository.findById(MarchandiseId).orElse(null);
    	if (marchandise == null) {
    		  Folder folder = folderService.getFolderById(folderId);
    	      Agrement agrement = agrementService.getAgrementById(marchandiseDTO.getAgrementId());

    	        Marchandise newMarchandise = new Marchandise();
    	        newMarchandise.setNature(marchandiseDTO.getNature());
    	        newMarchandise.setNombre(marchandiseDTO.getNombre());
    	        newMarchandise.setMarque(marchandiseDTO.getMarque());
    	        newMarchandise.setTransType(marchandiseDTO.getTransType());
    	        newMarchandise.setDescription(marchandiseDTO.getDescription());
    	        newMarchandise.setPaysProvenance(marchandiseDTO.getPaysProvenance());
    	        newMarchandise.setIncoterms(marchandiseDTO.getIncoterms());
    	        newMarchandise.setTypeCont(marchandiseDTO.getTypeCont());
    	        newMarchandise.setNumCont(marchandiseDTO.getNumCont());
    	        newMarchandise.setPoidb(marchandiseDTO.getPoidb());
    	        newMarchandise.setPoidn(marchandiseDTO.getPoidn());
    	        newMarchandise.setStatusMarchandise(marchandiseDTO.getStatusMarchandise());
    	        newMarchandise.setVolume(marchandiseDTO.getVolume());
    	        newMarchandise.setFranchise(marchandiseDTO.getFranchise());
    	        newMarchandise.setTypeRetard(marchandiseDTO.getTypeRetard());
    	        newMarchandise.setCommentaire(marchandiseDTO.getCommentaire());
    	        newMarchandise.setConComp(marchandiseDTO.getConComp());
    	        newMarchandise.setConBase(marchandiseDTO.getConBase());
    	        newMarchandise.setCompagnie(marchandiseDTO.getCompagnie());
    	        newMarchandise.setNVoyage(marchandiseDTO.getNVoyage());
    	        newMarchandise.setDateRec(marchandiseDTO.getDateRec());
    	        newMarchandise.setNavire(marchandiseDTO.getNavire());
    	        newMarchandise.setDatePrev(marchandiseDTO.getDatePrev());
    	        newMarchandise.setChargement(marchandiseDTO.getChargement());
    	        newMarchandise.setHawb(marchandiseDTO.getHawb());
    	        newMarchandise.setVol(marchandiseDTO.getVol());
    	        newMarchandise.setLtaN(marchandiseDTO.getLtaN());
    	        newMarchandise.setMagasin(marchandiseDTO.getMagasin());
    	        newMarchandise.setDechargement(marchandiseDTO.getDechargement());
    	        newMarchandise.setFolder(folder);
    	        newMarchandise.setAgrement(agrement);
        	    return marchandiseRepository.save(newMarchandise);      
    	}else {
  	      Agrement agrement = agrementService.getAgrementById(marchandiseDTO.getAgrementId());
        marchandise.setNature(marchandiseDTO.getNature());
        marchandise.setNombre(marchandiseDTO.getNombre());
        marchandise.setMarque(marchandiseDTO.getMarque());
        marchandise.setTransType(marchandiseDTO.getTransType());
        marchandise.setDescription(marchandiseDTO.getDescription());
        marchandise.setPaysProvenance(marchandiseDTO.getPaysProvenance());
        marchandise.setIncoterms(marchandiseDTO.getIncoterms());
        marchandise.setTypeCont(marchandiseDTO.getTypeCont());
        marchandise.setNumCont(marchandiseDTO.getNumCont());
        marchandise.setPoidb(marchandiseDTO.getPoidb());
        marchandise.setPoidn(marchandiseDTO.getPoidn());
        marchandise.setStatusMarchandise(marchandiseDTO.getStatusMarchandise());
        marchandise.setVolume(marchandiseDTO.getVolume());
        marchandise.setFranchise(marchandiseDTO.getFranchise());
        marchandise.setTypeRetard(marchandiseDTO.getTypeRetard());
        marchandise.setCommentaire(marchandiseDTO.getCommentaire());
        marchandise.setConComp(marchandiseDTO.getConComp());
        marchandise.setConBase(marchandiseDTO.getConBase());
        marchandise.setCompagnie(marchandiseDTO.getCompagnie());
        marchandise.setNVoyage(marchandiseDTO.getNVoyage());
        marchandise.setDateRec(marchandiseDTO.getDateRec());
        marchandise.setNavire(marchandiseDTO.getNavire());
        marchandise.setDatePrev(marchandiseDTO.getDatePrev());
        marchandise.setChargement(marchandiseDTO.getChargement());
        marchandise.setHawb(marchandiseDTO.getHawb());
        marchandise.setVol(marchandiseDTO.getVol());
        marchandise.setLtaN(marchandiseDTO.getLtaN());
        marchandise.setMagasin(marchandiseDTO.getMagasin());
        marchandise.setDechargement(marchandiseDTO.getDechargement());
        marchandise.setAgrement(agrement);

        return marchandiseRepository.save(marchandise);
    }
    }

    
    @Override
    public List<Marchandise> getAllMarchandiseByFolderId(Long folderId) {
        return marchandiseRepository.findByFolder_FolderId(folderId);
    }
}
