package projet.pfe.tms.services.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.RoleDTO;
import projet.pfe.tms.dto.RolePermissionDTO;
import projet.pfe.tms.models.Permissions;
import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Rolespermissions;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.repositories.PermissionRepo;
import projet.pfe.tms.repositories.RolePermissionRepo;
import projet.pfe.tms.repositories.RoleRepo;
import projet.pfe.tms.repositories.StaffRepo;
import projet.pfe.tms.services.RoleService;


import lombok.AllArgsConstructor;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    private final RoleRepo roleRepo;
    private final RolePermissionRepo rolePermissionRepository;
    private final PermissionRepo permissionRepository;
    
    @Autowired
    private RoleRepo roleRepository;
    
    @Autowired
    private RolePermissionRepo rolespermissionsRepository;
    
    @Autowired
    private StaffRepo staffRepository;
    
  
             //********************************** Create The Role ***************************************//
    @Transactional
    public Role createRoleWithPermission(RoleDTO roleDto) {

	    // Create new role and save to the database
	    Role newRole = new Role();
	    newRole.setRoleName(roleDto.getRoleName());
	    newRole.setRolePermissions(null);
	    roleRepository.save(newRole);
	    
	    Role savedRole = affecterPermissionsToRole(roleDto);
	    
	    return savedRole;
    }
    
    public Role affecterPermissionsToRole(RoleDTO roleDTO) {
    	
    	List<Rolespermissions> rolesPermissionsList = new ArrayList<Rolespermissions>();
    	
    	// Retrieve the role from database
    	Role role = roleRepo.findByRoleName(roleDTO.getRoleName());
    	
	    // Iterate over the list of roles-permissions and create a new Rolespermissions object for each one
	    for (RolePermissionDTO rolePermissionDTO : roleDTO.getRolePermissions()) {
	    	
	    	Rolespermissions rolesPermissions = new Rolespermissions();
	    	
	    	// Retrieve the permission from database
	    	Permissions permission = permissionRepository.findById(rolePermissionDTO.getPermissionId()).get();
	    	
	        // Create a new RolePermission object
	        rolesPermissions.setCanView(rolePermissionDTO.isCanView());
	        rolesPermissions.setCanEdit(rolePermissionDTO.isCanEdit());
	        rolesPermissions.setCanCreate(rolePermissionDTO.isCanCreate());
	        rolesPermissions.setCanDelete(rolePermissionDTO.isCanDelete());
	        rolesPermissions.setRole(role);
	        rolesPermissions.setPermission(permission);
	        
	        // add the roles-permissions to a list 
	        rolesPermissionsList.add(rolesPermissions);
	        
	        // Save the RolePermission object to the database
	        rolePermissionRepository.save(rolesPermissions);
	    }
    	
	    role.setRolePermissions(rolesPermissionsList);
	    
		return roleRepo.save(role);
    }
    
    
    //****************************************Get All Roles **************************************************//
    public List<RoleDTO> getAllRoles() {

	     List<Role> roles = roleRepository.findAll();
	     List<RoleDTO> roleDTOs = new ArrayList<>();

	     for (Role role : roles) {
	         RoleDTO roleDTO = new RoleDTO();
	         roleDTO.setRoleId(role.getRoleId());
	         roleDTO.setRoleName(role.getRoleName());
	         List<RolePermissionDTO> rolePermissionDTOs = new ArrayList<>();
	         for (Rolespermissions rp : role.getRolePermissions()) {
	             RolePermissionDTO rpDTO = new RolePermissionDTO();
	             rpDTO.setCanCreate(rp.isCanCreate());
	             rpDTO.setCanDelete(rp.isCanDelete());
	             rpDTO.setCanEdit(rp.isCanEdit());
	             rpDTO.setCanView(rp.isCanView());
	             rpDTO.setPermissionId(rp.getPermission().getIdPermission());
	             rolePermissionDTOs.add(rpDTO);
	         }
	         roleDTO.setRolePermissions(rolePermissionDTOs);
	         roleDTOs.add(roleDTO);
	     }

	     return roleDTOs;
	 }
   

    //****************************************Get Role By id **************************************************//

    public RoleDTO getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        List<RolePermissionDTO> rolePermissionDTOs = new ArrayList<>();
        for (Rolespermissions rp : role.getRolePermissions()) {
            RolePermissionDTO rpDTO = new RolePermissionDTO();
            rpDTO.setCanCreate(rp.isCanCreate());
            rpDTO.setCanDelete(rp.isCanDelete());
            rpDTO.setCanEdit(rp.isCanEdit());
            rpDTO.setCanView(rp.isCanView());
            rpDTO.setPermissionId(rp.getPermission().getIdPermission());
            rolePermissionDTOs.add(rpDTO);
        }
        roleDTO.setRolePermissions(rolePermissionDTOs);

        return roleDTO;
    }

    

   //*************************************** Update the Role **************************************//
	
    @Transactional
    public Role updateRole(Long roleId, RoleDTO roleDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));

        // Check if the new roleName already exists in the database
        String newRoleName = roleDto.getRoleName();
        Role existingRole = roleRepository.findByRoleName(newRoleName);
        if (existingRole != null && !existingRole.getRoleId().equals(roleId)) {
            // A role with the same name already exists, throw an exception or handle the error as appropriate
            throw new RuntimeException("Role name already exists in database");
        }

        // Update roleName
        role.setRoleName(newRoleName);

	    // Update role permissions
	    List<RolePermissionDTO> rolePermissionDTOs = roleDto.getRolePermissions();
	    List<Rolespermissions> rolesPermissionsList = role.getRolePermissions();
	    for (RolePermissionDTO rolePermissionDTO : rolePermissionDTOs) {
	        Optional<Rolespermissions> optionalRolePermission = rolesPermissionsList.stream()
	                .filter(rp -> rp.getPermission().getIdPermission().equals(rolePermissionDTO.getPermissionId()))
	                .findFirst();
	        if (optionalRolePermission.isPresent()) {
	            Rolespermissions rolePermission = optionalRolePermission.get();
	            rolePermission.setCanCreate(rolePermissionDTO.isCanCreate());
	            rolePermission.setCanDelete(rolePermissionDTO.isCanDelete());
	            rolePermission.setCanEdit(rolePermissionDTO.isCanEdit());
	            rolePermission.setCanView(rolePermissionDTO.isCanView());
	        } else {
	            // Create a new Rolespermissions object and add it to the list of role permissions
	            Rolespermissions newRolePermission = new Rolespermissions();
	            newRolePermission.setRole(role);
	            Permissions permission = permissionRepository.findById(rolePermissionDTO.getPermissionId())
	                    .orElseThrow(() -> new EntityNotFoundException("Permission not found"));
	            newRolePermission.setPermission(permission);
	            newRolePermission.setCanCreate(rolePermissionDTO.isCanCreate());
	            newRolePermission.setCanDelete(rolePermissionDTO.isCanDelete());
	            newRolePermission.setCanEdit(rolePermissionDTO.isCanEdit());
	            newRolePermission.setCanView(rolePermissionDTO.isCanView());
	            rolesPermissionsList.add(newRolePermission);
		        rolePermissionRepository.save(newRolePermission);

	        }
	    }

	    return roleRepository.save(role);
	}


    //*********************************************Delete the Role****************************************//
	@Transactional
	public void deleteRole(Long roleId) {
	    Role role = roleRepository.findById(roleId)
	        .orElseThrow(() -> new EntityNotFoundException("Role not found"));

	    // Update staffs with the given role
	    List<Staff> staffList = staffRepository.findByRole(role);
	    for (Staff staff : staffList) {
	        staff.setRole(null);
	        staffRepository.save(staff);
	    }

	    roleRepository.delete(role);
	}
	
	//****************Count the Role*************//

	
		 @Override
		    public Long countStaffByRole(String roleName) {
		        return staffRepository.countByRole_RoleName(roleName);
		    }
	
	
	


}
