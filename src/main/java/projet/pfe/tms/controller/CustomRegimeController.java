package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.CustomsOffice;
import projet.pfe.tms.models.CustomsRegime;
import projet.pfe.tms.services.CustomsRegimeService;

import java.util.List;

@RestController
@RequestMapping("api/customs-regimes")
public class CustomRegimeController {
    private final CustomsRegimeService customsRegimeService;

    @Autowired
    public CustomRegimeController(CustomsRegimeService customsRegimeService) {
        this.customsRegimeService = customsRegimeService;
    }

    @GetMapping("/")
    public List<CustomsRegime> getAllCustomsRegimes(){
        return this.customsRegimeService.getAllCustomsRegimes();
    }

    @GetMapping("/{id}")
    public CustomsRegime getCustomsRegimeById(@PathVariable Long id){
        return this.customsRegimeService.getCustomsRegimeById(id);
    }
}
