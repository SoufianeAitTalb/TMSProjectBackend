package projet.pfe.tms.services;


import projet.pfe.tms.models.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    Bank getBankById(Long id);

}
