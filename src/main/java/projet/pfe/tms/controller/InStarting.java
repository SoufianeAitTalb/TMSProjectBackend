package projet.pfe.tms.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import projet.pfe.tms.services.InStartingService;

@RestController
public class InStarting {
	
	@Autowired
	private InStartingService inStartingService;
	
    @PostConstruct
    public void initRoleAndUser() {
    	inStartingService.initDatabase();
    }	
}
