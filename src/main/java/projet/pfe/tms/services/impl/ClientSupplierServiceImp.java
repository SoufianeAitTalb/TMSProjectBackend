package projet.pfe.tms.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.pfe.tms.dto.ClientSupplierDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.ClientSupplier;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.repositories.ClientRepo;
import projet.pfe.tms.repositories.ClientSupplierRepo;
import projet.pfe.tms.repositories.SupplierRepo;
import projet.pfe.tms.services.ClientSupplierService;

@Service
public class ClientSupplierServiceImp implements ClientSupplierService {
	
	@Autowired
	private ClientSupplierRepo clientSupplierRepository;
	
	@Autowired
	private SupplierRepo supplierRepository;
	
	@Autowired
	private ClientRepo clientRepository;
	
	@Override
	public ClientSupplierDTO createClientSupplier(ClientSupplierDTO clientSupplierDTO) {
		Supplier supplier = supplierRepository.findById(clientSupplierDTO.getSupplierId())
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
		Client client = clientRepository.findById(clientSupplierDTO.getClientId())
				.orElseThrow(() -> new RuntimeException("Client not found"));
		ClientSupplier clientSupplier = ClientSupplier.builder()
				.supplier(supplier)
				.client(client)
				.build();
		clientSupplierRepository.save(clientSupplier);
		clientSupplierDTO.setId(clientSupplier.getId());
		return clientSupplierDTO;
	}

	@Override
	public ClientSupplierDTO updateClientSupplier(ClientSupplierDTO clientSupplierDTO) {
		ClientSupplier clientSupplier = clientSupplierRepository.findById(clientSupplierDTO.getId())
				.orElseThrow(() -> new RuntimeException("ClientSupplier not found"));
		Supplier supplier = supplierRepository.findById(clientSupplierDTO.getSupplierId())
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
		Client client = clientRepository.findById(clientSupplierDTO.getClientId())
				.orElseThrow(() -> new RuntimeException("Client not found"));
		clientSupplier.setSupplier(supplier);
		clientSupplier.setClient(client);
		clientSupplierRepository.save(clientSupplier);
		return clientSupplierDTO;
	}

	@Override
	public void deleteClientSupplier(Long id) {
		clientSupplierRepository.deleteById(id);
	}
	
	 @Override
	    public List<ClientSupplierDTO> getAllClientSuppliers() {
	        List<ClientSupplier> clientSuppliers = clientSupplierRepository.findAll();
	        return clientSuppliers.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    private ClientSupplierDTO convertToDTO(ClientSupplier clientSupplier) {
	        return ClientSupplierDTO.builder()
	                .id(clientSupplier.getId())
	                .supplierId(clientSupplier.getSupplier().getUserId())
	                .clientId(clientSupplier.getClient().getClientId())
	                .build();
	    }

}
