package projet.pfe.tms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.Bank;
import projet.pfe.tms.services.BankService;

import java.util.List;

@RestController
@RequestMapping("api/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public List<Bank> getAllBanks(){
        return this.bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable Long id){
        return this.bankService.getBankById(id);
    }

}
