package projet.pfe.tms.services;

import projet.pfe.tms.models.CustomsOffice;
import projet.pfe.tms.models.CustomsRegime;

import java.util.List;

public interface CustomsRegimeService {

    List<CustomsRegime> getAllCustomsRegimes();

    CustomsRegime getCustomsRegimeById(Long id);

}
