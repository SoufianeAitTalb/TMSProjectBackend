package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.pfe.tms.models.Rolespermissions;

public interface RolePermissionRepo extends JpaRepository<Rolespermissions, Integer> {

}
