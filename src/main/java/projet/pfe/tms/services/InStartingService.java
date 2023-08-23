package projet.pfe.tms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.*;

@Service
public class InStartingService {
	
	@Autowired
	private StaffRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PermissionRepo permissionRepo;

	@Autowired
	private SupplierRepo supplierRepo;

	@Autowired
	private RecipientRepo recipientRepo;

	public void initDatabase() {

		// Les Roles
		Role roleAdmin = new Role();
		roleAdmin.setRoleName("ADMIN");
		roleRepo.save(roleAdmin);

		Role roleEmployee = new Role();
		roleEmployee.setRoleName("EMPLOYEE");
		roleRepo.save(roleEmployee);

		// Les Permissions
		Permissions perms1 = new Permissions();
		perms1.setName("Contracts");
		perms1.setShortName("contracts");
		permissionRepo.save(perms1);

		Permissions perms2 = new Permissions();
		perms2.setName("Tasks");
		perms2.setShortName("tasks");
		permissionRepo.save(perms2);

		Permissions perms3 = new Permissions();
		perms3.setName("Reports");
		perms3.setShortName("reports");
		permissionRepo.save(perms3);

		Permissions perms4 = new Permissions();
		perms4.setName("Settings");
		perms4.setShortName("settings");
		permissionRepo.save(perms4);

		// Countries
//		Country ctr0 = new Country();
//		ctr0.setCode("PT");
//		ctr0.setName("Portugal");
//		countryRepo.save(ctr0);
//
//		Country ctr1 = new Country();
//		ctr1.setCode("MA");
//		ctr1.setName("Morocco");
//		countryRepo.save(ctr1);
//
//		Country ctr2 = new Country();
//		ctr2.setCode("MX");
//		ctr2.setName("Mexico");
//		countryRepo.save(ctr2);
//
//		Country ctr3 = new Country();
//		ctr3.setCode("FR");
//		ctr3.setName("France");
//		countryRepo.save(ctr3);
//
//		Country ctr4 = new Country();
//		ctr4.setCode("Qatar");
//		ctr4.setName("QA");
//		countryRepo.save(ctr4);

		// Currencies
//		Currency cr1 = new Currency();
//		cr1.setCode("USD");
//		cr1.setName("US Dollar");
//		currencyRepo.save(cr1);
//
//		Currency cr2 = new Currency();
//		cr2.setCode("GBP");
//		cr2.setName("British Pound Sterling");
//		currencyRepo.save(cr2);
//
//		Currency cr3 = new Currency();
//		cr3.setCode("EUR");
//		cr3.setName("Euro");
//		currencyRepo.save(cr3);
//
//		Currency cr4 = new Currency();
//		cr4.setCode("MAD");
//		cr4.setName("Moroccan Dirham");
//		currencyRepo.save(cr4);

		// Collaborateurs
		if(!userRepo.findByEmail("alisamari@gmail.com").isPresent()) {

			Staff user = new Staff();
			user.setEmail("alisamari@gmail.com");
			user.setPassword(passwordEncoder.encode("maroc12345"));
			user.setFirstName("Ali");
			user.setLastName("Samari");
			userRepo.save(user);

			accountService.affectRoleToUser(user.getEmail(), "EMPLOYEE");

		}

		if(!userRepo.findByEmail("elgazimohamed44@gmail.com").isPresent()) {

			Staff user = new Staff();
			user.setEmail("elgazimohamed44@gmail.com");
			user.setPassword(passwordEncoder.encode("tetouan123"));
			user.setFirstName("Mohamed");
			user.setLastName("GHZ");
			user.setAdmin(true);
			userRepo.save(user);

			accountService.affectRoleToUser(user.getEmail(), "ADMIN");

		}

		if(!userRepo.findByEmail("soufianenajji@gmail.com").isPresent()) {

			Staff user = new Staff();
			user.setEmail("soufianenajji@gmail.com");
			user.setPassword(passwordEncoder.encode("tetouan123"));
			user.setFirstName("Soufiane");
			user.setLastName("NAJJI");
			user.setAdmin(false);
			userRepo.save(user);

			accountService.affectRoleToUser(user.getEmail(), "EMPLOYEE");

		}
/*
		Supplier supplier1 = new Supplier();
		supplier1.setCompany("AWE Sender");
		supplier1.setCodeAuxi("FD562356");
		supplier1.setAddress("Avenue Far");
		supplier1.setCity("Tetouan");
		supplier1.setZip("93000");
		supplier1.setState("Tanger-Tetouan");
		supplier1.setPhoneNumber("0655552222");
		supplier1.setWebsite("www.awesender.com");
		this.supplierRepo.save(supplier1);


		Supplier supplier2 = new Supplier();
		supplier2.setCompany("AFA Sender");
		supplier2.setCodeAuxi("JH65655");
		supplier2.setAddress("Avenue MED 5");
		supplier2.setCity("Tetouan");
		supplier2.setZip("93000");
		supplier2.setState("Tanger-Tetouan");
		supplier2.setPhoneNumber("0622335588");
		supplier2.setWebsite("www.afasender.com");
		this.supplierRepo.save(supplier2);


		Recipient recipient1 = new Recipient();
		recipient1.setName("Said Kamal");
		recipient1.setAddress("Avenue Aicha, casablanca");
		this.recipientRepo.save(recipient1);

		Recipient recipient2 = new Recipient();
		recipient2.setName("Karim Kamal");
		recipient2.setAddress("Avenue Afassi, casablanca");
		this.recipientRepo.save(recipient2);
*/
	}
	
}
