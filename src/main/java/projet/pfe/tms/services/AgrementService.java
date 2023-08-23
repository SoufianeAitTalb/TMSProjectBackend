package projet.pfe.tms.services;


import projet.pfe.tms.models.Agrement;
import java.util.List;
public interface AgrementService {
	    List<Agrement> getAllAgrements();
	    Agrement createAgrement(Agrement agrement);
		Agrement getAgrementById(Long agrementId);

}
