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

import projet.pfe.tms.dto.DumDTO;
import projet.pfe.tms.models.Dum;
import projet.pfe.tms.services.DumService;


@RestController
@RequestMapping("/folders/{folderId}/dum")
public class DumController {
	
    @Autowired
    private DumService dumService;

    @PostMapping
    public Dum addDum(@PathVariable Long folderId, @RequestBody DumDTO dumDTO) {
        return dumService.addDum(folderId, dumDTO);
    }

    @PutMapping("/{id}")
    public Dum updateDum(@PathVariable Long folderId, @PathVariable Long id, @RequestBody DumDTO dumDTO) {
        return dumService.updateDum(folderId, id, dumDTO);
    }

    @GetMapping
    public List<Dum> getAllDum(@PathVariable Long folderId) {
        return dumService.getAllDumByFolderId(folderId);
    }

}
