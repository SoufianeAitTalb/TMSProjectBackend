package projet.pfe.tms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import projet.pfe.tms.dto.StaffDTO;
import projet.pfe.tms.models.Staff;
import projet.pfe.tms.services.StaffService;
import projet.pfe.tms.services.impl.StaffServiceImp;

import javax.websocket.server.PathParam;

@EnableWebMvc
@RestController
@RequestMapping("api/users")
public class StaffController {
	
	private final StaffService userService;

	@Autowired
	public StaffController(StaffServiceImp userService) {
		this.userService = userService;
	}

	@GetMapping("/")
//	@PostAuthorize("hasAuthority('ADMIN')")
	public List<Staff> listStaff() {
		return userService.listStaffs();
	}

	@GetMapping("/{id}")
//	@PostAuthorize("hasAuthority('ADMIN')")
	public Staff getUserById(@PathVariable Long id) {
		return userService.loadUserById(id);
	}

	@PostMapping("/login")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> login(@RequestBody StaffDTO staffDto){
		Map<String, String> response = new HashMap<>();
		response.put("status", "error");
		response.put("message", "Email ou mot de passe incorrect");
		if( this.userService.loadUserByEmail(staffDto.getEmail()) == null )
			return ResponseEntity.badRequest().body(response);
		else {
			if (!this.userService.loadUserByEmail(staffDto.getEmail()).getPassword().equals(staffDto.getPassword()))
				return ResponseEntity.badRequest().body(response);
			response.put("status", "success");
			response.put("message", "Login avec succès");
			return ResponseEntity.ok(response);
		}

	}

	@PostMapping("/add-staff")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addNewStaff(@RequestBody StaffDTO staffDto){

		if( this.userService.loadUserByEmail(staffDto.getEmail()) != null )
			return ResponseEntity.badRequest().body("Cet email est déjà utilisé");

		if( this.userService.addNewStaff(staffDto) != null )
			return ResponseEntity.ok("L'inscription a été réussi");

		return ResponseEntity.badRequest().body("Échec de l'enregistrement");
	}

	@PutMapping("/update-staff/{id}")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateStaff(@PathVariable Long id, @RequestBody StaffDTO staffDto){

		if( userService.updateStaff(id, staffDto) != null )
			return ResponseEntity.ok("La modification a été réussie");

		return ResponseEntity.badRequest().body("Échec de la modification");
	}

	@PutMapping("/set-new-pass-key")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> setNewPassKey( @RequestBody StaffDTO staffDto){
		if(staffDto.getEmail()!=null){
			if( userService.setNewPassKey(staffDto.getEmail(),staffDto.getNewPassKey())!=null)
				return ResponseEntity.ok("La modification a été réussie");
			return ResponseEntity.badRequest().body("Échec de la modification");
		}
		return ResponseEntity.badRequest().body("Échec de la modification");
	}

	@PutMapping("/change-password")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> changePassword( @RequestBody StaffDTO staffDto){
		if(staffDto.getEmail()!=null){
			if( userService.changePassword(staffDto.getEmail(),staffDto.getPassword())!=null)
				return ResponseEntity.ok("La modification a été réussie");
			return ResponseEntity.badRequest().body("Échec de la modification");
		}
		return ResponseEntity.badRequest().body("Échec de la modification");
	}

	@DeleteMapping("/delete-staff/{id}")
	//@PostAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteStaff(@PathVariable Long id){
		userService.deleteStaff(id);
		return ResponseEntity.ok("La suppression a été réussie");
	}

	@PostMapping("/does-user-exist")
	public boolean doesUserExists(@RequestBody StaffDTO staffDTO){
		if(staffDTO.getEmail()!=null){
			return this.userService.doesUserExistByEmail(staffDTO.getEmail());
		}
		return false;
	}

}
