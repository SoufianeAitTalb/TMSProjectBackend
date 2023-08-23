package projet.pfe.tms.services.impl;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.ClientDTO;
import projet.pfe.tms.models.*;
import projet.pfe.tms.repositories.ClientRepo;
import projet.pfe.tms.repositories.FolderRepo;
import projet.pfe.tms.services.ClientService;
import projet.pfe.tms.services.CountryService;
import projet.pfe.tms.services.CurrencyService;
import projet.pfe.tms.services.StaffService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {

    private final ClientRepo clientRepo;
    private final CountryService countryService;
    private final CurrencyService currencyService;
    private final StaffService staffService;
    private final FolderRepo folderRepo;

    @Autowired
    public ClientServiceImp(ClientRepo clientRepo,
                            CountryService countryService,
                            CurrencyService currencyService,
                            StaffService staffService, FolderRepo folderRepo){
        this.clientRepo = clientRepo;
        this.countryService = countryService;
        this.currencyService = currencyService;
        this.staffService = staffService;
        this.folderRepo = folderRepo;
    }

    @Transactional
    @Override
    public Client addNewClient(ClientDTO clientDto) {
        Client client = new Client();

        client.setCompany(clientDto.getCompany());
        client.setIceClient(clientDto.getIceClient());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setEmail(clientDto.getEmail());
        client.setEnCours(clientDto.getEnCours());
        client.setEcheance(clientDto.getEcheance());
        client.setWebsite(clientDto.getWebsite());
        client.setRC(clientDto.getRc());
        client.setCNSS(clientDto.getCnss());

        client.setAddress(clientDto.getAddress());
        client.setCity(clientDto.getCity());
        client.setZip(clientDto.getZip());

        // set client's country
        if(clientDto.getCountryId() != null) {
            Country clientCountry = this.countryService.getCountryById(clientDto.getCountryId());
            client.setCountry(clientCountry);
        }

        client.setCodeComptable(clientDto.getCodeComptable());
        client.setCodeAuxi(clientDto.getCodeAuxi());
        client.setPatente(clientDto.getPatente());

        // set client's currency
        if(clientDto.getDefaultCurrencyId() != null) {
            Currency clientCurrency = this.currencyService.getCurrencyById(clientDto.getDefaultCurrencyId());
            client.setCurrency(clientCurrency);
        }

        client.setBillingStreet(clientDto.getBillingStreet());
        client.setBillingState(clientDto.getBillingState());
        client.setBillingCity(clientDto.getBillingCity());
        client.setBillingZip(clientDto.getBillingZip());

        // set client billing country
        if(clientDto.getBillingCountryId() != null) {
            Country billingCountry = this.countryService.getCountryById(clientDto.getBillingCountryId());
            client.setBillingCountry(billingCountry);
        }

        client.setShippingStreet(clientDto.getShippingStreet());
        client.setShippingState(clientDto.getShippingState());
        client.setShippingCity(clientDto.getShippingCity());
        client.setShippingZip(clientDto.getShippingZip());

        // set client shipping country
        if(clientDto.getShippingCountryId() != null) {
            Country shippingCountry = this.countryService.getCountryById(clientDto.getShippingCountryId());
            client.setShippingCountry(shippingCountry);
        }

        return this.clientRepo.save(client);
    }

    @Transactional
    @Override
    public Client updateClient(Long id, ClientDTO clientDto) {
        Client client = this.clientRepo.findById(id).orElse(null);

        if(client != null){

            client = this.updateData(client, clientDto);

            // set client's currency
            Currency clientCurrency = this.currencyService.getCurrencyById(clientDto.getDefaultCurrencyId());
            client.setCurrency(clientCurrency);

            // set client's country
            Country clientCountry = this.countryService.getCountryById(clientDto.getCountryId());
            client.setCountry(clientCountry);

            // set client billing country
            Country billingCountry = this.countryService.getCountryById(clientDto.getBillingCountryId());
            client.setBillingCountry(billingCountry);

            // set client shipping country
            Country shippingCountry = this.countryService.getCountryById(clientDto.getShippingCountryId());
            client.setShippingCountry(shippingCountry);

            // set the collaborator in charge of that customer
            Staff collaborator = this.staffService.loadUserById(clientDto.getStaffId());
            client.setStaff(collaborator);

            return this.clientRepo.save(client);
        }
        return null;
    }

    @Override
    public Client updateData(Client client, ClientDTO clientDto) {
        client.setCompany(clientDto.getCompany());//
        client.setIceClient(clientDto.getIceClient());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setEmail(clientDto.getEmail());
        client.setEnCours(clientDto.getEnCours());
        client.setEcheance(clientDto.getEcheance());
        client.setWebsite(clientDto.getWebsite());
        client.setRC(clientDto.getRc());//
        client.setCNSS(clientDto.getCnss());

        client.setAddress(clientDto.getAddress());
        client.setCity(clientDto.getCity());//
        client.setZip(clientDto.getZip());

        client.setCodeComptable(clientDto.getCodeComptable());
        client.setCodeAuxi(clientDto.getCodeAuxi());
        client.setPatente(clientDto.getPatente());

        client.setBillingStreet(clientDto.getBillingStreet());//
        client.setBillingState(clientDto.getBillingState());
        client.setBillingCity(clientDto.getBillingCity());
        client.setBillingZip(clientDto.getBillingZip());

        client.setShippingStreet(clientDto.getShippingStreet());
        client.setShippingState(clientDto.getShippingState());
        client.setShippingCity(clientDto.getShippingCity());
        client.setShippingZip(clientDto.getShippingZip());

        return client;
    }

    @Override
    public void deleteClient(Long id) {

        clientRepo.deleteById(id);
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));


        List<Folder> folderList = folderRepo.findFolderByClient(client);
        for (Folder folder : folderList) {
            folder.setClient(null);
            folderRepo.save(folder);
        }

        clientRepo.delete(client);
    }

    @Override
    public List<Client> listClients() {
        return this.clientRepo.findAll();
    }

    @Override
    public List<ClientDTO> listClientsDTO() {
        List<Client> clients = this.clientRepo.findAll();
        List<ClientDTO> clientsDTO = clients.stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
        return clientsDTO; // Return the list of ClientDTOs
    }

    public ClientDTO convertToClientDTO(Client client) {
         if(client == null){
            return null;
        }
        ClientDTO clientDto = new ClientDTO();
         clientDto.setName(client.getName());
        clientDto.setClientId(client.getClientId());
        clientDto.setCompany(client.getCompany());
        clientDto.setIceClient(client.getIceClient());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setEmail(client.getEmail());
        clientDto.setEnCours(client.getEnCours());
        clientDto.setEcheance(client.getEcheance());
        clientDto.setWebsite(client.getWebsite());
        clientDto.setRc(client.getRC());
        clientDto.setCnss(client.getCNSS());

        clientDto.setAddress(client.getAddress());
        clientDto.setCity(client.getCity());
        clientDto.setZip(client.getZip());
        if(client.getCountry() != null){
            clientDto.setCountryId(client.getCountry().getCountryId());
            clientDto.setCountryLongName(client.getCountry().getLongName());}

        clientDto.setCodeComptable(client.getCodeComptable());
        clientDto.setCodeAuxi(client.getCodeAuxi());
        clientDto.setPatente(client.getPatente());
        if(client.getCurrency() != null)
            clientDto.setDefaultCurrencyId(client.getCurrency().getCurrencyId());

        clientDto.setBillingStreet(client.getBillingStreet());
        clientDto.setBillingState(client.getBillingState());
        clientDto.setBillingCity(client.getBillingCity());
        clientDto.setBillingZip(client.getBillingZip());
        if(client.getBillingCountry() != null)
            clientDto.setBillingCountryId(client.getBillingCountry().getCountryId());

        clientDto.setShippingStreet(client.getShippingStreet());
        clientDto.setShippingState(client.getShippingState());
        clientDto.setShippingCity(client.getShippingCity());
        clientDto.setShippingZip(client.getShippingZip());
        if(client.getShippingCountry() != null)
            clientDto.setShippingCountryId(client.getShippingCountry().getCountryId());

        if(client.getStaff() != null) {
            clientDto.setStaffId(client.getStaff().getStaffId());
            clientDto.setStaffFullName(client.getStaff().getFirstName() + " " + client.getStaff().getLastName());
            clientDto.setDateAffectation(client.getDateAffectationOfCommercial());
        }

        return clientDto;
    }

    @Override
    public Client loadClientById(Long id) {
        return this.clientRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public ClientDTO loadClientByClientId(Long id) {
        Client client = this.clientRepo.findById(id).orElse(null);
        if(client == null){
            return null;
        }
        ClientDTO clientDto = new ClientDTO();

        clientDto.setCompany(client.getCompany());
        clientDto.setIceClient(client.getIceClient());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setEmail(client.getEmail());
        clientDto.setEnCours(client.getEnCours());
        clientDto.setEcheance(client.getEcheance());
        clientDto.setWebsite(client.getWebsite());
        clientDto.setRc(client.getRC());
        clientDto.setCnss(client.getCNSS());

        clientDto.setAddress(client.getAddress());
        clientDto.setCity(client.getCity());
        clientDto.setZip(client.getZip());
        if(client.getCountry() != null)
            clientDto.setCountryId(client.getCountry().getCountryId());

        clientDto.setCodeComptable(client.getCodeComptable());
        clientDto.setCodeAuxi(client.getCodeAuxi());
        clientDto.setPatente(client.getPatente());
        if(client.getCurrency() != null)
            clientDto.setDefaultCurrencyId(client.getCurrency().getCurrencyId());

        clientDto.setBillingStreet(client.getBillingStreet());
        clientDto.setBillingState(client.getBillingState());
        clientDto.setBillingCity(client.getBillingCity());
        clientDto.setBillingZip(client.getBillingZip());
        if(client.getBillingCountry() != null)
            clientDto.setBillingCountryId(client.getBillingCountry().getCountryId());

        clientDto.setShippingStreet(client.getShippingStreet());
        clientDto.setShippingState(client.getShippingState());
        clientDto.setShippingCity(client.getShippingCity());
        clientDto.setShippingZip(client.getShippingZip());
        if(client.getShippingCountry() != null)
            clientDto.setShippingCountryId(client.getShippingCountry().getCountryId());

        if(client.getStaff() != null) {
            clientDto.setStaffId(client.getStaff().getStaffId());
            clientDto.setStaffFullName(client.getStaff().getFirstName() + " " + client.getStaff().getLastName());
            clientDto.setDateAffectation(client.getDateAffectationOfCommercial());
        }

        return clientDto;
    }


    @Override
    public Client loadClientByEmail(String email) {
        return this.clientRepo
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public Client loadClientByCompany(String company) {
        return this.clientRepo
                .findByCompany(company)
                .orElse(null);
    }

    @Override
    public Client loadClientByIceClient(String iceClient) {
        return this.clientRepo
                .findByIceClient(iceClient)
                .orElse(null);
    }

    @Override
    public Client affectCommercialToClient(Long idClient, Long idStaff) {
        Client client = this.loadClientById(idClient);
        Staff staff = this.staffService.loadUserById(idStaff);
        if(client != null){
            client.setStaff(staff);
            client.setDateAffectationOfCommercial(LocalDateTime.now());
            return this.clientRepo.save(client);
        }
        return null;
    }

    @Override
    public Client deleteCommercialOfClient(Long id) {
        Client client = this.loadClientById(id);
        client.setStaff(null);
        client.setDateAffectationOfCommercial(null);
        return this.clientRepo.save(client);
    }

    @Override
    public int countTotalClients() {
        return (int) clientRepo.count();
    }

    @Override
    public int countActiveClients() {
        return clientRepo.countByActive(true);
    }

    @Override
    public int countInactiveClients() {
        return clientRepo.countByActive(false);
    }

    @Override
    public Client updateClientActiveStatus(Long clientId, boolean active) {
        Client client = clientRepo.findByClientId(clientId);
        client.setActive(active);
        return clientRepo.save(client);
    }
}
