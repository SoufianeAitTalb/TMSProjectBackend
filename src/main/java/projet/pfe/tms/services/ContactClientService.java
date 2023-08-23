package projet.pfe.tms.services;

import projet.pfe.tms.dto.ContactClientDTO;
import projet.pfe.tms.models.ContactClient;

import java.util.List;

public interface ContactClientService {

    ContactClient addNewContact(ContactClientDTO contactDto);
    ContactClient updateContact(Long id, ContactClientDTO contactDto);
    ContactClient updateData(ContactClient contact, ContactClientDTO contactDto);
    void deleteClient(Long id);
    List<ContactClient> listContacts();
    ContactClient loadContactById(Long id);
    ContactClientDTO loadContactByClientId(Long id);
    ContactClient loadContactByEmail(String email);
    List<ContactClient> getAllContactsByClient(Long id);
    ContactClient addClientContact(ContactClientDTO contactDto);
    ContactClient updateClientContact(Long clientId,Long id, ContactClientDTO contactDto);
    void deleteClientContact(Long id);
    String getContactPrincipalFullName(Long id);
    String getContactPrincipalEmail(Long id);
    ContactClient updateContactActiveStatus(Long contactId, boolean active);
    int countTotalContacts();
    int countActiveContacts();
    int countInactiveContacts();
}
