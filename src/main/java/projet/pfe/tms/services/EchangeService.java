package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.dto.EchangeDTO;
import projet.pfe.tms.models.Echange;

public interface EchangeService {
    Echange addEchange(Long folderId, EchangeDTO echangeDTO);

    Echange updateEchange(Long folderId, Long echangeId, EchangeDTO echangeDTO);

    List<Echange> getAllEchangesByFolderId(Long folderId);
}