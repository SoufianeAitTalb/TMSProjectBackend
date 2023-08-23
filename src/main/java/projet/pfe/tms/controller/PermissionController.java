package projet.pfe.tms.controller;

import java.util.List;

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
import projet.pfe.tms.models.Permissions;
import projet.pfe.tms.services.PermissionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("permissions")
@AllArgsConstructor
public class PermissionController {

    private PermissionService permissionService;

    @GetMapping("/afficher")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public List<Permissions> afficherAll() {
        return permissionService.lire();
    }

    @GetMapping("/afficher/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public Permissions afficherById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping("/create")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<String> ajouterPermission(@RequestBody Permissions permission) {
        if(permissionService.createPermission(permission) != null)
            return ResponseEntity.ok("La permission a été ajoutée avec succès");
        return ResponseEntity.badRequest().body("Échec de l'opération d'ajout");
    }

    @PutMapping("/modifier/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<String> modifierPermission(@PathVariable Long id, @RequestBody Permissions permission) {
        if(permissionService.updatePermission(id, permission) != null)
            return ResponseEntity.ok("La permission a été modifiée avec succès");
        return ResponseEntity.badRequest().body("Échec de l'opération de modification");
    }

    @DeleteMapping("/supprimer/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public ResponseEntity<String> supprimerPermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok("La permission a été supprimée avec succès");
    }
}
