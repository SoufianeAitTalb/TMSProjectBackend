package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.dto.ContactClientDTO;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.ContactClient;
import projet.pfe.tms.repositories.ClientRepo;
import projet.pfe.tms.repositories.ContactClientRepo;
import projet.pfe.tms.services.ClientService;
import projet.pfe.tms.services.ContactClientService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactClientServiceImp implements ContactClientService {
    private final ContactClientRepo contactClientRepo;
    private final ClientService clientService;

    @Autowired
    public ContactClientServiceImp(ContactClientRepo contactClientRepo, ClientService clientService) {
        this.contactClientRepo = contactClientRepo;
        this.clientService = clientService;
    }

    @Autowired
    private ClientRepo clientRepo;

    @Transactional
    @Override
    public ContactClient addNewContact(ContactClientDTO contactDto) {
        ContactClient contact = new ContactClient();

        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setDirection(contactDto.getDirection());
        contact.setTitle(contactDto.getTitle());
        contact.setProfileImage(contact.getProfileImage());
        contact.setPrimary(contactDto.isPrimary());

        Client client = this.clientService.loadClientById(contactDto.getClientId());
        contact.setClient(client);

        return this.contactClientRepo.save(contact);
    }

    @Override
    public ContactClient addClientContact(ContactClientDTO contactDto) {

        ContactClient contactClient = new ContactClient();

        contactClient.setFirstName(contactDto.getFirstName());
        contactClient.setLastName(contactDto.getLastName());
        contactClient.setEmail(contactDto.getEmail());
        contactClient.setPhoneNumber(contactDto.getPhoneNumber());
        contactClient.setDirection(contactDto.getDirection());
        contactClient.setTitle(contactDto.getTitle());
        contactClient.setProfileImage(contactClient.getProfileImage());
        contactClient.setPrimary(contactDto.isPrimary());


        Long clientId = contactDto.getClientId();
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id: " + clientId));
        contactClient.setClient(client);

        contactClientRepo.save(contactClient);

        return contactClient;
    }

    @Override
    public ContactClient updateClientContact(Long clientId ,Long id, ContactClientDTO contactDto) {

        ContactClient contactClient = contactClientRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));

        contactClient.setFirstName(contactDto.getFirstName());
        contactClient.setLastName(contactDto.getLastName());
        contactClient.setEmail(contactDto.getEmail());
        contactClient.setPhoneNumber(contactDto.getPhoneNumber());
        contactClient.setDirection(contactDto.getDirection());
        contactClient.setTitle(contactDto.getTitle());
        contactClient.setProfileImage(contactClient.getProfileImage());
        contactClient.setPrimary(contactDto.isPrimary());

        return contactClientRepo.save(contactClient);
    }

    @Override
    public ContactClient updateContact(Long id, ContactClientDTO contactDto) {
        ContactClient contact = this.loadContactById(id);

        if(contact != null) {

            contact = this.updateData(contact, contactDto);

            Client client = this.clientService.loadClientById(contactDto.getClientId());
            contact.setClient(client);

            return this.contactClientRepo.save(contact);
        }

        return null;
    }

    @Override
    public ContactClient updateData(ContactClient contact, ContactClientDTO contactDto) {

        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setDirection(contactDto.getDirection());
        contact.setTitle(contactDto.getTitle());
        contact.setProfileImage(contact.getProfileImage());
        contact.setPrimary(contactDto.isPrimary());

        return contact;
    }

    @Override
    public void deleteClientContact(Long id) {
        contactClientRepo.deleteById(id);
    }

    @Override
    public String getContactPrincipalFullName(Long id) {
        Client client = this.clientService.loadClientById(id);

        List<ContactClient> contacts = client.getContacts();
        for (ContactClient contact : contacts) {
            if (contact.isPrimary()) {
                return contact.getFirstName() + " " + contact.getLastName();
            }
        }
        return null;
    }

    @Override
    public String getContactPrincipalEmail(Long id) {
        Client client = this.clientService.loadClientById(id);

        List<ContactClient> contacts = client.getContacts();
        for (ContactClient contact : contacts) {
            if (contact.isPrimary()) {
                return contact.getEmail();
            }
        }
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        this.contactClientRepo.deleteById(id);
    }

    @Override
    public List<ContactClient> listContacts() {
        return this.contactClientRepo.findAll();
    }

    @Override
    public ContactClient loadContactById(Long id) {
        return this.contactClientRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public ContactClientDTO loadContactByClientId(Long id) {

        ContactClient contact = this.loadContactById(id);

        if(contact != null){

            ContactClientDTO contactDTO = new ContactClientDTO();
            contactDTO.setFirstName(contact.getFirstName());
            contactDTO.setLastName(contact.getLastName());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setPhoneNumber(contact.getPhoneNumber());
            contactDTO.setDirection(contact.getDirection());
            contactDTO.setTitle(contact.getTitle());
            contactDTO.setActive(contact.getActive());
            contactDTO.setProfileImage(contact.getProfileImage());
            contactDTO.setPrimary(contact.isPrimary());

            contactDTO.setClientId(contact.getClient().getClientId());
            contactDTO.setCompany(contact.getClient().getCompany());

            return contactDTO;
        }
        return null;
    }

    @Override
    public ContactClient loadContactByEmail(String email) {
        return this.contactClientRepo
                .findByEmail(email)
                .orElse(null);
    }

    @Override
    public List<ContactClient> getAllContactsByClient(Long id) {
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client not found with id: " + id));
        return client.getContacts();
    }
    @Override
    public ContactClient updateContactActiveStatus(Long contactId, boolean active) {
        ContactClient contactClient = contactClientRepo.findByContactId(contactId);
        if(contactClient != null){
            contactClient.setActive(active);
            return contactClientRepo.save(contactClient);
        }
        else{
            return null;
        }
    }

    @Override
    public int countTotalContacts() {
        return (int) contactClientRepo.count();
    }

    @Override
    public int countActiveContacts() {
        return contactClientRepo.countByActive(true);
    }

    @Override
    public int countInactiveContacts() {
        return contactClientRepo.countByActive(false);
    }
}