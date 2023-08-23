package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.Country;
import projet.pfe.tms.repositories.CountryRepo;
import projet.pfe.tms.repositories.CurrencyRepo;
import projet.pfe.tms.services.CountryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    private final CountryRepo countryRepo;

    @Autowired
    public CountryServiceImp(CountryRepo countryRepo){
        this.countryRepo = countryRepo;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country with this id not found"));
    }
}
