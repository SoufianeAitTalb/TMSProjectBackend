package projet.pfe.tms.services;

import projet.pfe.tms.models.CustomsOffice;

import java.util.List;

public interface CustomsOfficeService {

    List<CustomsOffice> getAllCustomsOffices();

    CustomsOffice getCustomsOfficeById(Long id);

}
