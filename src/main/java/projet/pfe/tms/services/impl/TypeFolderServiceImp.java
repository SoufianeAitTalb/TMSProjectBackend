package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.TypeFolder;
import projet.pfe.tms.repositories.TypeFolderRepo;
import projet.pfe.tms.services.TypeFolderService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TypeFolderServiceImp implements TypeFolderService  {

    private final TypeFolderRepo typeFolderRepo;

    @Autowired
    public TypeFolderServiceImp(TypeFolderRepo typeFolderRepo) {
        this.typeFolderRepo = typeFolderRepo;
    }

    @Override
    public List<TypeFolder> getAllTypesOfFolders() {
        return this.typeFolderRepo.findAll();
    }

    @Override
    public TypeFolder getTypeOfFolderById(Long id) {
        return this.typeFolderRepo
                .findById(id)
                .orElse(null);
    }
}
