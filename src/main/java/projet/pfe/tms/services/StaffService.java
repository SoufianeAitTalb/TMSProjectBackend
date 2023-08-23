package projet.pfe.tms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.StaffDTO;
import projet.pfe.tms.models.Staff;

@Service
public interface StaffService {

	Staff addNewStaff(StaffDTO staffDto);

	Staff updateStaff(Long id, StaffDTO staffDto);

	void deleteStaff(Long id);

	List<Staff> listStaffs();

	Staff loadUserByEmail(String email);

	Staff loadUserById(Long id);

}
