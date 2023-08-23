package projet.pfe.tms.services;

import projet.pfe.tms.models.TypeFolder;

import java.util.List;

public interface TypeFolderService {

    List<TypeFolder> getAllTypesOfFolders();

    TypeFolder getTypeOfFolderById(Long id);


}
