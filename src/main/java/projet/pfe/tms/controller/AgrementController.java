package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.pfe.tms.models.Agrement;
import projet.pfe.tms.services.AgrementService;

import java.util.List;

@RestController
@RequestMapping("/api/agrements")
public class AgrementController {

    @Autowired
    private AgrementService agrementService;

    @GetMapping
    public ResponseEntity<List<Agrement>> getAllAgrements() {
        List<Agrement> agrements = agrementService.getAllAgrements();
        return ResponseEntity.ok(agrements);
    }


    @PostMapping
    public ResponseEntity<Agrement> createAgrement(@RequestBody Agrement agrement) {
        Agrement createdAgrement = agrementService.createAgrement(agrement);
        return new ResponseEntity<>(createdAgrement, HttpStatus.CREATED);
    }
    
   
    
}
