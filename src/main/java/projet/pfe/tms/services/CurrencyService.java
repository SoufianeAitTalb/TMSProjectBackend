package projet.pfe.tms.services;

import projet.pfe.tms.models.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrencies();

    Currency getCurrencyById(Long id);
}

