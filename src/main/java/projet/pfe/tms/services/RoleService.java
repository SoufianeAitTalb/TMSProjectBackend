package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.dto.RoleDTO;
import projet.pfe.tms.models.Role;

public interface RoleService {

	RoleDTO getRoleById(Long roleId);
 	
    List<RoleDTO> getAllRoles();
    
    Role updateRole(Long roleId, RoleDTO roleDto);
    
    void deleteRole(Long id);
      
    Role createRoleWithPermission(RoleDTO roleDTO);
    
    Long countStaffByRole(String roleName);
}
