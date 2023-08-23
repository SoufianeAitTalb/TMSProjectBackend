package projet.pfe.tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projet.pfe.tms.dto.EchangeDTO;
import projet.pfe.tms.dto.MarchandiseDTO;
import projet.pfe.tms.models.Echange;
import projet.pfe.tms.models.Marchandise;
import projet.pfe.tms.services.MarchandiseService;

@RestController
@RequestMapping("/folders/{folderId}/marchandises")
public class MarchandiseController {
	
	@Autowired
	private MarchandiseService marchandiseService;
	
	  @PostMapping
	    public Marchandise addMarchandise(@PathVariable Long folderId, @RequestBody MarchandiseDTO marchandiseDTO) {
	        return marchandiseService.addMarchandise(folderId, marchandiseDTO);
	    }
	  @PutMapping("/{id}")
	    public Marchandise updateMarchandise(@PathVariable Long folderId, @PathVariable Long id, @RequestBody MarchandiseDTO marchandiseDTO) {
	        return marchandiseService.updateMarchandise(folderId, id, marchandiseDTO);
	    }
	  @GetMapping
	    public List<Marchandise> getAllMarchandises(@PathVariable Long folderId) {
	        return marchandiseService.getAllMarchandiseByFolderId(folderId);
	    }
}
