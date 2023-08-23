package projet.pfe.tms.services;

import projet.pfe.tms.dto.FolderDTO;
import projet.pfe.tms.models.Folder;

import java.util.List;

public interface FolderService {

    Folder createFolder(FolderDTO folderDTO);

    Folder updateFolder(FolderDTO folderDTO, Long id);

    void deleteFolder(Long folderId);

    List<FolderDTO> getAllFolders();

    Folder loadFolderById(Long id);

    FolderDTO loadFolderByFolderId(Long id);

    Folder getFolderById(Long folderId);

    String getClientCompany(Long folderId);

}
