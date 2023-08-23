package projet.pfe.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Staff;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

	// Or, you can use a method name query:

	Optional<Staff> findByEmail(String email);
	Optional<Staff> findByStaffId(Long id);
	boolean existsByEmail(String email);
	
	Long countByRole_RoleName(String roleName);
	
    List<Staff> findByRole(Role role);


}
