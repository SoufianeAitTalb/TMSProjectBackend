package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.dto.MarchandiseDTO;
import projet.pfe.tms.models.Marchandise;

public interface MarchandiseService {

    Marchandise addMarchandise(Long folderId, MarchandiseDTO marchandiseDTO);
    List<Marchandise> getAllMarchandiseByFolderId(Long folderId);
    Marchandise updateMarchandise(Long folderId,Long MarchandiseId, MarchandiseDTO marchandiseDTO);
}
