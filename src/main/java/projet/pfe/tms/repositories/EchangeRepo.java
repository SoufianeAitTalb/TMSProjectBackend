package projet.pfe.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.pfe.tms.dto.EchangeDTO;
import projet.pfe.tms.models.Echange;

public interface EchangeRepo extends JpaRepository<Echange, Long> {
    List<Echange> findByFolder_FolderId(Long folderId);
}
