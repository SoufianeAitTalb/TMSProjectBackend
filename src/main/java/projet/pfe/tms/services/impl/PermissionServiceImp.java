package projet.pfe.tms.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import projet.pfe.tms.models.Permissions;
import projet.pfe.tms.repositories.PermissionRepo;
import projet.pfe.tms.services.PermissionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissionServiceImp implements PermissionService {

    private final PermissionRepo permissionRepo;

    @Override
    public Permissions createPermission(Permissions permission) {
        return this.permissionRepo.save(permission);
    }

    @Override
    public List<Permissions> lire() {
        return this.permissionRepo.findAll();
    }

    @Override
    public Permissions getPermissionById(Long id) {
        return this.permissionRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public Permissions updatePermission(Long id, Permissions permission) {
        return permissionRepo.findById(id).map(p -> {
            p.setName(permission.getName());
            p.setShortName(permission.getShortName());
            return permissionRepo.save(p);
        }).orElse(null);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepo.deleteById(id);
    }

}

