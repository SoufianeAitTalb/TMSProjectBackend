package projet.pfe.tms.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.TypeFolder;
import projet.pfe.tms.services.TypeFolderService;

import java.util.List;

@RestController
@RequestMapping("api/types-of-folders")
public class TypeFolderController {

    private final TypeFolderService typeFolderService;

    @Autowired
    public TypeFolderController(TypeFolderService typeFolderService) {
        this.typeFolderService = typeFolderService;
    }

    @GetMapping("/")
    public List<TypeFolder> getAllTypesOfFolders(){
        return this.typeFolderService.getAllTypesOfFolders();
    }

    @GetMapping("/{id}")
    public TypeFolder getTypeOfFolderById(@PathVariable Long id){
        return this.typeFolderService.getTypeOfFolderById(id);
    }
}
