package projet.pfe.tms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.EchangeDTO;
import projet.pfe.tms.models.Echange;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.repositories.EchangeRepo;
import projet.pfe.tms.services.EchangeService;
import projet.pfe.tms.services.FolderService;

@Service
public class EchangeServiceImp implements EchangeService {

    @Autowired
    private EchangeRepo echangeRepository;

    @Autowired
    private FolderService folderService;

    @Override
    public Echange addEchange(Long folderId, EchangeDTO echangeDTO) {
        Folder folder = folderService.getFolderById(folderId);
        Echange echange = new Echange();
        echange.setCoursier(echangeDTO.getCoursier());
        echange.setStatusEchange(echangeDTO.getStatusEchange());
        echange.setNatureEchange(echangeDTO.getNatureEchange());
        echange.setDateReceBad(echangeDTO.getDateReceBad());
        echange.setDateLettreRes(echangeDTO.getDateLettreRes());
        echange.setDateReceCheque(echangeDTO.getDateReceCheque());
        echange.setDateRemiseCheque(echangeDTO.getDateRemiseCheque());
        echange.setFolder(folder);
        return echangeRepository.save(echange);
    }

    @Override
    public Echange updateEchange(Long folderId, Long echangeId, EchangeDTO echangeDTO) {
    	Echange echange = echangeRepository.findById(echangeId).orElse(null);
    	if (echange == null) {
    	    Folder folder = folderService.getFolderById(folderId);
    	    Echange newEchange = new Echange();
    	    newEchange.setCoursier(echangeDTO.getCoursier());
    	    newEchange.setStatusEchange(echangeDTO.getStatusEchange());
    	    newEchange.setNatureEchange(echangeDTO.getNatureEchange());
    	    newEchange.setDateReceBad(echangeDTO.getDateReceBad());
    	    newEchange.setDateLettreRes(echangeDTO.getDateLettreRes());
    	    newEchange.setDateReceCheque(echangeDTO.getDateReceCheque());
    	    newEchange.setDateRemiseCheque(echangeDTO.getDateRemiseCheque());
    	    newEchange.setFolder(folder);
    	    return echangeRepository.save(newEchange);
    	} else {
    	    echange.setCoursier(echangeDTO.getCoursier());
    	    echange.setStatusEchange(echangeDTO.getStatusEchange());
    	    echange.setNatureEchange(echangeDTO.getNatureEchange());
    	    echange.setDateReceBad(echangeDTO.getDateReceBad());
    	    echange.setDateLettreRes(echangeDTO.getDateLettreRes());
    	    echange.setDateReceCheque(echangeDTO.getDateReceCheque());
    	    echange.setDateRemiseCheque(echangeDTO.getDateRemiseCheque());
    	    return echangeRepository.save(echange);
    	}

    }

    @Override
    public List<Echange> getAllEchangesByFolderId(Long folderId) {
        return echangeRepository.findByFolder_FolderId(folderId);
    }
}
