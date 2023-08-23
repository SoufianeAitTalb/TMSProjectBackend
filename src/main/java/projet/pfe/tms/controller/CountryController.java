package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.Country;
import projet.pfe.tms.services.CountryService;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController( CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/")
    public List<Country> getAllCountries(){
        return this.countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id){
        return this.countryService.getCountryById(id);
    }

}
