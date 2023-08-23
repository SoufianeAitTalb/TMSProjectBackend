package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.SupplierDTO;
import projet.pfe.tms.models.Folder;
import projet.pfe.tms.models.Recipient;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.repositories.SupplierRepo;
import projet.pfe.tms.services.CountryService;
import projet.pfe.tms.services.CurrencyService;
import projet.pfe.tms.services.SupplierService;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CurrencyService currencyService;

    @Override
    public Supplier addSupplier(SupplierDTO supplierDTO) {

        Supplier supplier = new Supplier();
        supplier.setCompany(supplierDTO.getCompany());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplier.setCountry(this.countryService.getCountryById(supplierDTO.getCountryId()));
        supplier.setCity(supplierDTO.getCity());
        supplier.setZip(supplierDTO.getZip());
        supplier.setState(supplierDTO.getState());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setWebsite(supplierDTO.getWebsite());
        supplier.setDefaultCurrency(this.currencyService.getCurrencyById(supplierDTO.getDefaultCurrencyId()));
        supplier.setCodeAuxi(supplierDTO.getCodeAuxi());

        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long userId, SupplierDTO supplierDTO) {

        Supplier supplier = supplierRepo.findByUserId(userId);

        supplier.setCompany(supplierDTO.getCompany());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        supplier.setCountry(this.countryService.getCountryById(supplierDTO.getCountryId()));
        supplier.setCity(supplierDTO.getCity());
        supplier.setZip(supplierDTO.getZip());
        supplier.setState(supplierDTO.getState());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setWebsite(supplierDTO.getWebsite());
        supplier.setDefaultCurrency(this.currencyService.getCurrencyById(supplierDTO.getDefaultCurrencyId()));
        supplier.setCodeAuxi(supplierDTO.getCodeAuxi());
        return supplierRepo.save(supplier);
    }

    @Override
    public SupplierDTO loadSupplierById(Long userId) {
        Supplier supplier = supplierRepo.findById(userId).orElse(null);
        if (supplier == null) {
            return null;
        }
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setCompany(supplier.getCompany());
        supplierDTO.setDefaultCurrencyId(supplier.getDefaultCurrency().getCurrencyId());
        supplierDTO.setCountryId(supplier.getCountry().getCountryId());
        supplierDTO.setPhoneNumber(supplier.getPhoneNumber());
        supplierDTO.setCity(supplier.getCity());
        supplierDTO.setZip(supplier.getZip());
        supplierDTO.setState(supplier.getState());
        supplierDTO.setAddress(supplier.getAddress());
        supplierDTO.setWebsite(supplier.getWebsite());
        supplierDTO.setCodeAuxi(supplier.getCodeAuxi());
        ///
        return supplierDTO;
    }

    @Override
    public Supplier loadById(Long userId) {
        Supplier supplier = supplierRepo.findById(userId).orElse(null);
        if (supplier == null) {
            return null;
        }
        return supplier;
    }


    @Override
    public Supplier updateSupplierActiveStatus(Long userId, boolean active) {
        Supplier supplier = supplierRepo.findByUserId(userId);
        supplier.setActive(active);
        return supplierRepo.save(supplier);
    }


    @Override
    public Supplier loadSupplierByCompany(String company) {
        return supplierRepo.findSupplierByCompany(company);
    }

    @Override
    public void deleteSupplier(Long userId) {

        supplierRepo.deleteById(userId);
        Supplier supplier = supplierRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));


        List<Folder> folderList = folderRepo.findFolderBySupplier(supplier);
        for (Folder folder : folderList) {
            folder.setSupplier(null);
            folderRepo.save(folder);
        }

        supplierRepo.delete(supplier);
    }
    @Override
    public List<Supplier> listSuppliers() {

        return supplierRepo.findAll();
    }
    @Override
    public int countTotalSuppliers() {
        return (int) supplierRepo.count();
    }

    @Override
    public int countActiveSuppliers() {
        return supplierRepo.countByActive(true);
    }

    @Override
    public int countInactiveSuppliers() {
        return supplierRepo.countByActive(false);
    }
}
