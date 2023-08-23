package projet.pfe.tms.services;

import java.util.List;

import projet.pfe.tms.dto.ClientSupplierDTO;
import projet.pfe.tms.models.ClientSupplier;

public interface ClientSupplierService {

    ClientSupplierDTO createClientSupplier(ClientSupplierDTO clientSupplierDTO);

    ClientSupplierDTO updateClientSupplier(ClientSupplierDTO clientSupplierDTO);

    void deleteClientSupplier(Long id);

    List<ClientSupplierDTO> getAllClientSuppliers();


}
