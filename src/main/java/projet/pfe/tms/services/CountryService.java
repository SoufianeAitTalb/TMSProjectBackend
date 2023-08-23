package projet.pfe.tms.services;

import projet.pfe.tms.models.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries();

    Country getCountryById(Long id);

}
