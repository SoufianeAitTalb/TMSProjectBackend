package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.FolderDTO;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.*;
import projet.pfe.tms.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImp implements FolderService {

    private final FolderRepo folderRepo;
    private final SupplierService supplierService;
    private final ClientService clientService;
    private final RecipientService recipientService;
    private final TypeFolderService typeFolderService;
    private final CustomsOfficeService customsOfficeService;
    private final CustomsRegimeService customsRegimeService;
    private final RecipientRepo recipientRepo;
    private final SupplierRepo supplierRepo;
    private final ClientRepo clientRepo;
    private final TypeFolderRepo typeFolderRepo;
    private final CustomsOfficeRepo customsOfficeRepo;
    private final CustomsRegimeRepo customsRegimeRepo;

    @Autowired
    public FolderServiceImp(FolderRepo folderRepo, SupplierService supplierService, ClientService clientService,
            RecipientService recipientService, TypeFolderService typeFolderService,
            CustomsOfficeService customsOfficeService, CustomsRegimeService customsRegimeService,
            RecipientRepo recipientRepo, SupplierRepo supplierRepo, ClientRepo clientRepo,
            TypeFolderRepo typeFolderRepo, CustomsOfficeRepo customsOfficeRepo,
            CustomsRegimeRepo customsRegimeRepo) {
        this.folderRepo = folderRepo;
        this.supplierService = supplierService;
        this.clientService = clientService;
        this.recipientService = recipientService;
        this.typeFolderService = typeFolderService;
        this.customsOfficeService = customsOfficeService;
        this.customsRegimeService = customsRegimeService;
        this.recipientRepo = recipientRepo;
        this.supplierRepo = supplierRepo;
        this.clientRepo = clientRepo;
        this.typeFolderRepo = typeFolderRepo;
        this.customsOfficeRepo = customsOfficeRepo;
        this.customsRegimeRepo = customsRegimeRepo;
    }

    @Override
    @Transactional
    public Folder createFolder(FolderDTO folderDTO) {
        Folder folder = new Folder();

        folder.setReferenceClient(folderDTO.getReferenceClient());
        folder.setNumRep1(folderDTO.getNumRep1());
        folder.setDateFinished(folderDTO.getDateFinished());
        folder.setOpeningType(folderDTO.getOpeningType());
        folder.setDescription(folderDTO.getDescription());

        Supplier supplier = this.supplierService.loadById(folderDTO.getSupplierId());
        if (supplier != null) {
            folder.setSupplier(supplier);
        } else {
            folder.setSupplier(null);
        }

        Client client = this.clientService.loadClientById(folderDTO.getClientId());
        if (client != null) {
            folder.setClient(client);
        } else {
            folder.setClient(null);
        }
        Recipient recipient = this.recipientService.getRecipientById(folderDTO.getRecipientId());
        if (recipient != null) {
            folder.setRecipient(recipient);
        } else {
            folder.setRecipient(null);
        }

        TypeFolder typeFolder = this.typeFolderService.getTypeOfFolderById(folderDTO.getTypeFolderId());
        if (typeFolder != null) {
            folder.setTypeFolder(typeFolder);
        } else {
            folder.setTypeFolder(null);
        }

        CustomsOffice customsOffice = this.customsOfficeService.getCustomsOfficeById(folderDTO.getCustomsOfficeId());
        if (customsOffice != null) {
            folder.setCustomsOffice(customsOffice);
        } else {
            folder.setCustomsOffice(null);
        }

        CustomsRegime customsRegime = this.customsRegimeService.getCustomsRegimeById(folderDTO.getCustomsRegimeId());
        if (customsRegime != null) {
            folder.setCustomsRegime(customsRegime);
        } else {
            folder.setCustomsRegime(null);
        }

        return this.folderRepo.save(folder);
    }

    @Override
    public Folder updateFolder(FolderDTO folderDTO, Long id) {
        Folder folder = this.loadFolderById(id);
        if (folder != null) {

            folder.setReferenceClient(folderDTO.getReferenceClient());
            folder.setNumRep1(folderDTO.getNumRep1());
            folder.setDateFinished(folderDTO.getDateFinished());
            folder.setOpeningType(folderDTO.getOpeningType());
            folder.setDescription(folderDTO.getDescription());

            Supplier supplier = this.supplierService.loadById(folderDTO.getSupplierId());
            if (supplier != null)
                folder.setSupplier(supplier);

            // Client client = this.clientService.loadClientById(folderDTO.getClientId());
            // if(client != null)
            // folder.setClient(client);

            Recipient recipient = this.recipientService.getRecipientById(folderDTO.getRecipientId());
            if (recipient != null)
                folder.setRecipient(recipient);

            TypeFolder typeFolder = this.typeFolderService.getTypeOfFolderById(folderDTO.getTypeFolderId());
            if (typeFolder != null)
                folder.setTypeFolder(typeFolder);

            CustomsOffice customsOffice = this.customsOfficeService
                    .getCustomsOfficeById(folderDTO.getCustomsOfficeId());
            if (customsOffice != null)
                folder.setCustomsOffice(customsOffice);

            CustomsRegime customsRegime = this.customsRegimeService
                    .getCustomsRegimeById(folderDTO.getCustomsRegimeId());
            if (customsRegime != null)
                folder.setCustomsRegime(customsRegime);

            return this.folderRepo.save(folder);
        }
        return null;
    }

    @Override
    public List<FolderDTO> getAllFolders() {
        List<Folder> folders = this.folderRepo.findAll();
        List<FolderDTO> folderDTOs = new ArrayList<>();
        for (Folder folder : folders) {

            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setFolderId(folder.getFolderId());
            folderDTO.setReferenceClient(folder.getReferenceClient());
            folderDTO.setNumRep1(folder.getNumRep1());
            folderDTO.setOpeningType(folder.getOpeningType());
            folderDTO.setDescription(folder.getDescription());
            folderDTO.setDateFinished(folder.getDateFinished());

            folderDTO.setSupplierId(folder.getSupplier() != null ? folder.getSupplier().getUserId() : null);
            folderDTO.setClientId(folder.getClient() != null ? folder.getClient().getClientId() : null);
            folderDTO.setRecipientId(folder.getRecipient() != null ? folder.getRecipient().getRecipientId() : null);
            folderDTO.setTypeFolderId(folder.getTypeFolder() != null ? folder.getTypeFolder().getTypeFolderId() : null);
            folderDTO.setCustomsOfficeId(
                    folder.getCustomsOffice() != null ? folder.getCustomsOffice().getCustomOfficeId() : null);
            folderDTO.setCustomsRegimeId(
                    folder.getCustomsRegime() != null ? folder.getCustomsRegime().getCustomsRegimeId() : null);

            folderDTOs.add(folderDTO);
        }
        return folderDTOs;
    }

    @Override
    public Folder loadFolderById(Long id) {
        return this.folderRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public FolderDTO loadFolderByFolderId(Long id) {
        Folder folder = this.folderRepo.findById(id).orElse(null);
        if (folder != null) {
            FolderDTO folderDTO = new FolderDTO();
            folderDTO.setFolderId(folder.getFolderId());
            folderDTO.setSupplierId(folder.getSupplier().getUserId());
            folderDTO.setClientId(folder.getClient().getClientId());
            folderDTO.setRecipientId(folder.getRecipient().getRecipientId());
            folderDTO.setReferenceClient(folder.getReferenceClient());
            folderDTO.setNumRep1(folder.getNumRep1());
            folderDTO.setDateFinished(folder.getDateFinished());
            folderDTO.setTypeFolderId(folder.getTypeFolder().getTypeFolderId());
            folderDTO.setOpeningType(folder.getOpeningType());
            folderDTO.setCustomsOfficeId(folder.getCustomsOffice().getCustomOfficeId());
            folderDTO.setCustomsRegimeId(folder.getCustomsRegime().getCustomsRegimeId());
            folderDTO.setDescription(folder.getDescription());

            return folderDTO;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteFolder(Long folderId) {
        Optional<Folder> optionalFolder = folderRepo.findById(folderId);
        if (optionalFolder.isPresent()) {
            Folder folder = optionalFolder.get();

            // Remove folder reference from recipient
            Recipient recipient = folder.getRecipient();
            if (recipient != null) {
                recipient.getRecipientFolders().remove(folder);
                recipientRepo.save(recipient);
            }

            // Remove folder reference from supplier
            Supplier supplier = folder.getSupplier();
            if (supplier != null) {
                supplier.getSupplierFolders().remove(folder);
                supplierRepo.save(supplier);
            }

            // Remove folder reference from client
            Client client = folder.getClient();
            if (client != null) {
                client.getClientFolders().remove(folder);
                clientRepo.save(client);
            }

            // Remove folder reference from typeFolder
            TypeFolder typeFolder = folder.getTypeFolder();
            if (typeFolder != null) {
                typeFolder.getFolders().remove(folder);
                typeFolderRepo.save(typeFolder);
            }

            // Remove folder reference from customsOffice
            CustomsOffice customsOffice = folder.getCustomsOffice();
            if (customsOffice != null) {
                customsOffice.getCustomsOfficeFolders().remove(folder);
                customsOfficeRepo.save(customsOffice);
            }

            // Remove folder reference from customsRegime
            CustomsRegime customsRegime = folder.getCustomsRegime();
            if (customsRegime != null) {
                customsRegime.getCustomsRegimeFolders().remove(folder);
                customsRegimeRepo.save(customsRegime);
            }

            /*
             * Remove folder reference from agent
             * Agent agent = folder.getAgent();
             * if (agent != null) {
             * agent.getAgentFolders().remove(folder);
             * agentRepo.save(agent);
             * }
             */

            // Delete the folder
            folderRepo.delete(folder);
        } else {
            throw new RuntimeException("Folder not found with id " + folderId);
        }
    }
        @Override
        public Folder getFolderById(Long folderId) {
            return folderRepo.findById(folderId).orElse(null);
        }

        @Override
        public String getClientCompany(Long folderId) {
            Folder folder = folderRepo.findById(folderId).orElse(null);
            if (folder != null) {
                Client client = clientService.loadClientById(folder.getClient().getClientId());
                if (client != null) {
                    return client.getCompany();
                }
            }
            return null;
        }
}
