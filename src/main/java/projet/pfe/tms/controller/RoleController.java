package projet.pfe.tms.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projet.pfe.tms.dto.RoleDTO;
import projet.pfe.tms.dto.RolePermissionDTO;
import projet.pfe.tms.models.Permissions;
import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Rolespermissions;
import projet.pfe.tms.repositories.PermissionRepo;
import projet.pfe.tms.repositories.RolePermissionRepo;
import projet.pfe.tms.repositories.RoleRepo;
import projet.pfe.tms.services.RoleService;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/roles")
@AllArgsConstructor
public class RoleController {

	private RoleService roleService;

	@Autowired
    private PermissionRepo permissionRepository;
	
	@Autowired
    private RoleRepo roleRepository;
	
	@Autowired
    private RolePermissionRepo rolePermissionRepository;



	@DeleteMapping("/delete-role/{id}")
	public void supprimerRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
	
	
	@PostMapping("/create-role")
	public ResponseEntity<String> createRoleWithPermission(@RequestBody RoleDTO roleDto) {
		
	    // Check if role with same name already exists in the database
	    Role existingRole = roleRepository.findByRoleName(roleDto.getRoleName());
	    if (existingRole != null)
	        return ResponseEntity.badRequest().body("Ce role existe déjà");
	    
	    if(roleService.createRoleWithPermission(roleDto) != null)		
	    	return ResponseEntity.ok("Le rôle a été créé avec succès");

	    return ResponseEntity.badRequest().body("Une erreur s'est produite lors du processus d'ajout");

	}

	@GetMapping("/")
	public List<RoleDTO> afficherAll() {
		return roleService.getAllRoles();
	}
	
	@PutMapping("/update-role/{roleId}")
	public ResponseEntity<?> updateRole(@PathVariable Long roleId, @RequestBody RoleDTO roleDto) {
	    try {
	        Role updatedRole = roleService.updateRole(roleId, roleDto);
	    	return ResponseEntity.ok("Le rôle a été modifiée avec succès");
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body("Ce role existe déjà");
	    }
	}

	
	@GetMapping("/{roleId}")
	public RoleDTO afficherById(@PathVariable Long roleId) {
		return roleService.getRoleById(roleId);
	}

	 @GetMapping("/count-role/{roleName}")
		public ResponseEntity<Long> countStaffByRole(@PathVariable String roleName) {
			Long count = roleService.countStaffByRole(roleName);
			return ResponseEntity.ok(count);
		}
}