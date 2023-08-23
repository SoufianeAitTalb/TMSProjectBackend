package projet.pfe.tms.services.impl;

import org.springframework.stereotype.Service;
import projet.pfe.tms.models.Bank;
import projet.pfe.tms.repositories.BankRepo;
import projet.pfe.tms.services.BankService;

import java.util.List;

@Service
public class BankServiceImp implements BankService {

    private final BankRepo bankRepo;

    public BankServiceImp(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    @Override
    public List<Bank> getAllBanks() {
        return this.bankRepo.findAll();
    }

    @Override
    public Bank getBankById(Long id) {
        return this.bankRepo
                .findById(id)
                .orElse(null);
    }
}
