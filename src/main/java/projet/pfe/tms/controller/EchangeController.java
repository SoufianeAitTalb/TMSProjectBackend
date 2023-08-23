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
import projet.pfe.tms.models.Echange;
import projet.pfe.tms.services.EchangeService;


@RestController
@RequestMapping("/folders/{folderId}/echanges")
public class EchangeController {

    @Autowired
    private EchangeService echangeService;

    @PostMapping
    public Echange addEchange(@PathVariable Long folderId, @RequestBody EchangeDTO echangeDTO) {
        return echangeService.addEchange(folderId, echangeDTO);
    }

    @PutMapping("/{id}")
    public Echange updateEchange(@PathVariable Long folderId, @PathVariable Long id, @RequestBody EchangeDTO echangeDTO) {
        return echangeService.updateEchange(folderId, id, echangeDTO);
    }

    @GetMapping
    public List<Echange> getAllEchanges(@PathVariable Long folderId) {
        return echangeService.getAllEchangesByFolderId(folderId);
    }
}
