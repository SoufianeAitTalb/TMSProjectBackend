package projet.pfe.tms.services;

import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.SupplierDTO;
import projet.pfe.tms.models.Supplier;

import java.util.List;

@Service
public interface SupplierService {

    SupplierDTO loadSupplierById(Long userId);
    Supplier loadById(Long userId);


    Supplier loadSupplierByCompany(String Company);
    Supplier addSupplier(SupplierDTO supplierDTO);

    Supplier updateSupplier(Long userId, SupplierDTO supplierDTO);

    void deleteSupplier(Long userId);

    List<Supplier> listSuppliers();

    int countTotalSuppliers();

    int countActiveSuppliers();
    
    Supplier updateSupplierActiveStatus(Long userId, boolean active);


    int countInactiveSuppliers();
}
