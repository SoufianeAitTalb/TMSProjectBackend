package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.dto.DumDTO;
import projet.pfe.tms.models.Dum;

public interface DumService {
	
	   Dum addDum(Long folderId, DumDTO dumDTO);

	   Dum updateDum(Long folderId, Long dumId, DumDTO dumDTO);

	   List<Dum> getAllDumByFolderId(Long folderId);

}
