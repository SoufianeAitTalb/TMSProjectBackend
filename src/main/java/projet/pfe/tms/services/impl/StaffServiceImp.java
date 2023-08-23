package projet.pfe.tms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.StaffDTO;
import projet.pfe.tms.models.Role;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.repositories.RoleRepo;
import projet.pfe.tms.repositories.StaffRepo;
import projet.pfe.tms.services.RoleService;
import projet.pfe.tms.services.StaffService;

import javax.persistence.EntityNotFoundException;


@Service
public class StaffServiceImp implements StaffService {

    private final StaffRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final RoleService roleService;

    @Autowired
    public StaffServiceImp(StaffRepo userRepo,
                           PasswordEncoder passwordEncoder,
                           RoleRepo roleRepo,
                           RoleService roleService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.roleService = roleService;
    }

    @Override
    public List<Staff> listStaffs() {
        return userRepo.findAll();
    }

    @Override
    public Staff loadUserByEmail(String email) {
        return userRepo
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public Staff loadUserById(Long id) {
        return userRepo
                .findByStaffId(id)
                .orElse(null);
    }


    @Override
    public Staff addNewStaff(StaffDTO staffDto) {
        Staff user = new Staff();

        user.setFirstName(staffDto.getFirstName());
        user.setLastName(staffDto.getLastName());
        user.setEmail(staffDto.getEmail());
        user.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        user.setPhoneNumber(staffDto.getPhoneNumber());
        user.setProfileImage(staffDto.getProfileImage());
        user.setEmailSignature(staffDto.getEmailSignature());
        user.setAdmin(staffDto.isAdmin());

        Role role = roleRepo.findByRoleName("ADMIN");

        if(!staffDto.isAdmin()){
            role = this.roleService.updateRole(staffDto.getRoleDto().getRoleId(), staffDto.getRoleDto());
        }

        // Set the user's role
        user.setRole(role);
        return userRepo.save(user);
    }
    @Override
    public Staff updateStaff(Long id, StaffDTO staffDto){
        Staff user = this.loadUserById(id);

        user.setFirstName(staffDto.getFirstName());
        user.setLastName(staffDto.getLastName());
        user.setEmail(staffDto.getEmail());
        user.setPhoneNumber(staffDto.getPhoneNumber());
        user.setEmailSignature(staffDto.getEmailSignature());
        if(staffDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        user.setAdmin(staffDto.isAdmin());

        Role role = roleRepo.findByRoleName("ADMIN");

        if(!staffDto.isAdmin()){
            role = this.roleService.updateRole(staffDto.getRoleDto().getRoleId(), staffDto.getRoleDto());
        }

        user.setRole(role);
        return userRepo.save(user);
    }

    @Override
    public void deleteStaff(Long id){
        userRepo.deleteById(id);
    }


}
