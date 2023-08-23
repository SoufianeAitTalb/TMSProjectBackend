package projet.pfe.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.pfe.tms.models.Marchandise;

public interface MarchandiseRepo extends JpaRepository<Marchandise, Long>{
    List<Marchandise> findByFolder_FolderId(Long folderId);


}
