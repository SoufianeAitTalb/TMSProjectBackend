package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.SupplierContactDTO;
import projet.pfe.tms.dto.SupplierDTO;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.models.SupplierContact;
import projet.pfe.tms.repositories.SupplierContactRepo;
import projet.pfe.tms.repositories.SupplierRepo;
import projet.pfe.tms.services.CountryService;
import projet.pfe.tms.services.CurrencyService;
import projet.pfe.tms.services.SupplierContactService;
import projet.pfe.tms.services.SupplierService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class SupplierContactServiceImpl implements SupplierContactService {

    @Autowired
    private SupplierContactRepo supplierContactRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private   SupplierService supplierService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CurrencyService currencyService;
    @Override
    public SupplierContact addSupplierContact(SupplierContactDTO supplierContactDTO) {

        SupplierContact supplierContact = new SupplierContact();

        supplierContact.setFirstName(supplierContactDTO.getFirstName());
        supplierContact.setLastName(supplierContactDTO.getLastName());
        supplierContact.setEmail(supplierContactDTO.getEmail());
        supplierContact.setPhoneNumber(supplierContactDTO.getPhoneNumber());
        supplierContact.setPrimary(supplierContactDTO.isPrimary());

        Long supplierId = supplierContactDTO.getSupplierId();
        Supplier supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new NoSuchElementException("Supplier not found with id: " + supplierId));
        supplierContact.setSupplier(supplier);

        supplierContactRepo.save(supplierContact);

        return supplierContact;
    }

    @Override
    public SupplierContact updateSupplierContact(Long SupplierId ,Long id, SupplierContactDTO supplierContactDTO) {

        SupplierContact supplierContact = supplierContactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));

        supplierContact.setFirstName(supplierContactDTO.getFirstName());
        supplierContact.setLastName(supplierContactDTO.getLastName());
        supplierContact.setEmail(supplierContactDTO.getEmail());
        supplierContact.setPhoneNumber(supplierContactDTO.getPhoneNumber());
        supplierContact.setPrimary(supplierContactDTO.isPrimary());

       /* Supplier supplier = this.supplierService.loadSupplierById(supplierContactDTO.getSupplierId());
           supplierContact.setSupplier(supplier);*/

        return supplierContactRepo.save(supplierContact);
    }
    
  
    @Override
    public void deleteSupplierContact(Long id) {
        supplierContactRepo.deleteById(id);
    }

    @Override
    public SupplierContact loadSupplierContactById(Long id) {
        return supplierContactRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier contact not found"));
    }


    @Override
    public List<SupplierContact> getAllContactBySupplier(Long id) {
   Supplier supplier = supplierRepo.findById(id)
           .orElseThrow(() -> new NoSuchElementException("Supplier not found with id: " + id));
      return supplier.getSupplierContactList();
   }

    @Override
    public List<SupplierContact> loadAllSupplierContacts() {

        return supplierContactRepo.findAll();
    }
    
    
    @Override
    public String getPrincipalContactEmailBySupplier(Long id) {
        Supplier supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier not found with id: " + id));
        SupplierContact primaryContact = supplier.getSupplierContactList()
                .stream()
                .filter(contact -> contact.isPrimary())
                .findFirst()
                .orElse(null);
        return primaryContact != null ? primaryContact.getEmail() : null;
    }
    
    
    @Override
    public String getPrincipalFullNameBySupplier(Long id) {
        Supplier supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier not found with id: " + id));

        List<SupplierContact> contacts = supplier.getSupplierContactList();
        for (SupplierContact contact : contacts) {
            if (contact.isPrimary()) {
                return contact.getFirstName() + " " + contact.getLastName();
            }
        }
        return null;
    }
}
