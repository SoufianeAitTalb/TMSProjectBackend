package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.models.Permissions;
import projet.pfe.tms.models.Rolespermissions;

public interface RolepermissionService {

    Rolespermissions createRolespermissions(Rolespermissions rolespermissions);

    List<Rolespermissions> lire();

    Rolespermissions getRolespermissionsById(int id);

    Rolespermissions updateRolespermissions(int id, Rolespermissions rolespermissions);

    void deleteRolespermissions(int id);
}


