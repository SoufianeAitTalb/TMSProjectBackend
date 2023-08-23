package projet.pfe.tms.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import projet.pfe.tms.models.Rolespermissions;
import projet.pfe.tms.repositories.RolePermissionRepo;
import projet.pfe.tms.services.RolepermissionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RolepermissionServiceImp implements RolepermissionService {

    private final RolePermissionRepo rolespermissionsRepo;

    @Override
    public Rolespermissions createRolespermissions(Rolespermissions rolespermissions) {
        return this.rolespermissionsRepo.save(rolespermissions);
    }

    @Override
    public List<Rolespermissions> lire() {
        return this.rolespermissionsRepo.findAll();
    }

    @Override
    public Rolespermissions getRolespermissionsById(int id) {
        return this.rolespermissionsRepo.findById(id).orElseThrow(() -> new RuntimeException("Rolespermissions not found"));
    }

    @Override
    public Rolespermissions updateRolespermissions(int id, Rolespermissions rolespermissions) {
        return rolespermissionsRepo.findById(id).map(p -> {
            p.setCanView(rolespermissions.isCanView());
            p.setCanEdit(rolespermissions.isCanEdit());
            p.setCanCreate(rolespermissions.isCanCreate());
            p.setCanDelete(rolespermissions.isCanDelete());
            p.setRole(rolespermissions.getRole());
            p.setPermission(rolespermissions.getPermission());
            return rolespermissionsRepo.save(p);
        }).orElseThrow(() -> new RuntimeException("Rolespermissions not found"));
    }

    @Override
    public void deleteRolespermissions(int id) {

        rolespermissionsRepo.deleteById(id);
    }

}
