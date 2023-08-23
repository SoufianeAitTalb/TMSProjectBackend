package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.repositories.RoleRepo;
import projet.pfe.tms.repositories.StaffRepo;
import projet.pfe.tms.services.AccountService;

@Service
public class AccountServiceImp implements AccountService {

    private final StaffRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public AccountServiceImp(StaffRepo userRepo, RoleRepo roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Override
    public void affectRoleToUser(String email, String roleName) {
        Staff user = userRepo.findByEmail(email).get();
        Role role = roleRepo.findByRoleName(roleName);
        user.setRole(role);
        userRepo.save(user);
    }

    @Override
    public Staff resetPassword(Staff user, String password){
        user.setPassword(encoder.encode(password));
        return userRepo.save(user);
    }
}
