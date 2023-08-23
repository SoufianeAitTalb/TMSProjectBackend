package projet.pfe.tms.repositories;

import projet.pfe.tms.models.Ngp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.pfe.tms.models.Supplier;


import java.util.Optional;

@Repository
public interface NgpRepos extends JpaRepository<Ngp, Long> {

    Optional<Ngp> findByCodeProduct(String codeProduct);
    Ngp findByNgpId(Long Id);


}
