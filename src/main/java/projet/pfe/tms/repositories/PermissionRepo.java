package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.pfe.tms.models.Permissions;

import java.util.Optional;

public interface PermissionRepo extends JpaRepository<Permissions, Long> {
    Permissions findByIdPermission(Long id);
}
