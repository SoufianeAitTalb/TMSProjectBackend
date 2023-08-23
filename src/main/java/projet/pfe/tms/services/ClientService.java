package projet.pfe.tms.services;

import org.springframework.http.ResponseEntity;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.Staff;

import java.util.List;

public interface ClientService {

    Client addNewClient(ClientDTO clientDto);
    Client updateClient(Long id, ClientDTO clientDTO);
    Client updateData(Client client, ClientDTO clientDto);
    void deleteClient(Long id);
    List<ClientDTO> listClientsDTO();
    List<Client> listClients();
    Client loadClientByEmail(String email);
    Client loadClientById(Long id);
    ClientDTO loadClientByClientId(Long id);
    Client loadClientByCompany(String company);
    Client loadClientByIceClient(String iceClient);
    Client affectCommercialToClient(Long idClient, Long idStaff);
    Client deleteCommercialOfClient(Long id);

    int countTotalClients();
    int countActiveClients();
    int countInactiveClients();
    Client updateClientActiveStatus(Long clientId, boolean active);

}
