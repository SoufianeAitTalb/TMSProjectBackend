package projet.pfe.tms.services;

import org.springframework.stereotype.Service;

import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Staff;

@Service
public interface AccountService {

	void affectRoleToUser(String email, String roleName);
	Staff resetPassword(Staff user, String password);
}
