package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.Currency;
import projet.pfe.tms.repositories.CurrencyRepo;
import projet.pfe.tms.services.CurrencyService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CurrencyServiceImp implements CurrencyService {

    private final CurrencyRepo currencyRepo;

    @Autowired
    public CurrencyServiceImp(CurrencyRepo currencyRepo){
        this.currencyRepo = currencyRepo;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    @Override
    public Currency getCurrencyById(Long id) {
        return currencyRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Currency with this id not found"));
    }
}
