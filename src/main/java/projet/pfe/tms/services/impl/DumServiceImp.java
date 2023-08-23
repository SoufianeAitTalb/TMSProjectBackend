package projet.pfe.tms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.DumDTO;
import projet.pfe.tms.dto.EchangeDTO;
import projet.pfe.tms.models.Dum;
import projet.pfe.tms.models.Echange;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.repositories.DumRepo;
import projet.pfe.tms.services.DumService;
import projet.pfe.tms.services.FolderService;

@Service
public class DumServiceImp implements DumService{
	
	@Autowired
    private DumRepo dumRepository;

    @Autowired
    private FolderService folderService;

    @Override
    public Dum addDum(Long folderId, DumDTO dumDTO) {
        Folder folder = folderService.getFolderById(folderId);
        Dum dum = new Dum();
        dum.setNumDum(dumDTO.getNumDum());
        dum.setDateDeman(dumDTO.getDateDeman());
        dum.setDateDum(dumDTO.getDateDum());
        dum.setDateValid(dumDTO.getDateValid());
        dum.setFolder(folder);
        return dumRepository.save(dum);
    }
    
    @Override
    public Dum  updateDum(Long folderId, Long dumId, DumDTO dumDTO) {
    	Dum dum = dumRepository.findById(dumId).orElse(null);
    	if (dum == null) {
    	    Folder folder = folderService.getFolderById(folderId);
    	    Dum newDum = new Dum();
    	    newDum.setNumDum(dumDTO.getNumDum());
    	    newDum.setDateDeman(dumDTO.getDateDeman());
    	    newDum.setDateDum(dumDTO.getDateDum());
    	    newDum.setDateValid(dumDTO.getDateValid());
    	    newDum.setFolder(folder);
    	    return dumRepository.save(newDum);
    	} else {
            dum.setNumDum(dumDTO.getNumDum());
            dum.setDateDeman(dumDTO.getDateDeman());
            dum.setDateDum(dumDTO.getDateDum());
            dum.setDateValid(dumDTO.getDateValid());
    	    return dumRepository.save(dum);
    	}

    }
    
    @Override
    public List<Dum> getAllDumByFolderId(Long folderId) {
        return dumRepository.findByFolder_FolderId(folderId);
    }

}
