package projet.pfe.tms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import projet.pfe.tms.dto.ClientSupplierDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.ClientSupplier;
import projet.pfe.tms.services.ClientSupplierService;
@RestController
@RequestMapping("api/clientsuppliers")
public class ClientSupplierController {
	
	@Autowired
	private ClientSupplierService clientSupplierService;
	
	@PostMapping("/add-client-supplier")
	public ClientSupplierDTO createClientSupplier(@RequestBody ClientSupplierDTO clientSupplierDTO) {
		return clientSupplierService.createClientSupplier(clientSupplierDTO);
	}
	
	@PutMapping("/update-client-supplier/{id}")
	public ClientSupplierDTO updateClientSupplier(@PathVariable Long id, @RequestBody ClientSupplierDTO clientSupplierDTO) {
		clientSupplierDTO.setId(id);
		return clientSupplierService.updateClientSupplier(clientSupplierDTO);
	}
	
	@DeleteMapping("/delete-client-supplier/{id}")
	public void deleteClientSupplier(@PathVariable Long id) {
		clientSupplierService.deleteClientSupplier(id);
	}
	
	@GetMapping("/")
	 public List<ClientSupplierDTO> getAllClientSuppliers() {
        return clientSupplierService.getAllClientSuppliers();
    }
	
}

