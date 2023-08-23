package projet.pfe.tms.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import projet.pfe.tms.dto.SupplierContactDTO;
import projet.pfe.tms.models.SupplierContact;
import java.util.List;
@Service
public interface SupplierContactService {
    SupplierContact addSupplierContact(SupplierContactDTO supplierContactDTO);
    SupplierContact updateSupplierContact(Long supplierId,Long id, SupplierContactDTO supplierContactDTO);
    void deleteSupplierContact(Long id);
    List<SupplierContact> loadAllSupplierContacts();
    SupplierContact loadSupplierContactById(Long id);
    List<SupplierContact> getAllContactBySupplier(Long id);
    String getPrincipalContactEmailBySupplier(Long id);
    String getPrincipalFullNameBySupplier(Long id);
}
