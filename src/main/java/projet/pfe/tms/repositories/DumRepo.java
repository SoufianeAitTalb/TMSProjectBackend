package projet.pfe.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.pfe.tms.models.Dum;

public interface DumRepo extends JpaRepository<Dum, Long> {
    List<Dum> findByFolder_FolderId(Long folderId);

}
