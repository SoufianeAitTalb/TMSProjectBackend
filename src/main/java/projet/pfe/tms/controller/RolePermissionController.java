package projet.pfe.tms.controller;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.Rolespermissions;
import projet.pfe.tms.services.RolepermissionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("rolepermissions")
@AllArgsConstructor
public class RolePermissionController {

    private RolepermissionService rolePermissionService;

    @PostMapping("/create")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public Rolespermissions ajouterRolePermission(@RequestBody Rolespermissions rolePermission) {
        return rolePermissionService.createRolespermissions(rolePermission);
    }

    @GetMapping("/afficher")
//    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public List<Rolespermissions> afficherAll() {
        return rolePermissionService.lire();
    }

    @GetMapping("/afficher/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public Rolespermissions afficherById(@PathVariable int id) {
        return rolePermissionService.getRolespermissionsById(id);
    }

    @PutMapping("/modifier/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public Rolespermissions modifierRolePermission(@PathVariable int id, @RequestBody Rolespermissions rolePermission) {
        return rolePermissionService.updateRolespermissions(id, rolePermission);
    }

    @DeleteMapping("/supprimer/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public void supprimerRolePermission(@PathVariable int id) {
        rolePermissionService.deleteRolespermissions(id);
    }
}
