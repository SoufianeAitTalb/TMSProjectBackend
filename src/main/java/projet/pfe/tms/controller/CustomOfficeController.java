package projet.pfe.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.pfe.tms.models.CustomsOffice;
import projet.pfe.tms.services.CustomsOfficeService;
import java.util.List;

@RestController
@RequestMapping("api/customs-offices")
public class CustomOfficeController {

    private final CustomsOfficeService customsOfficeService;

    @Autowired
    public CustomOfficeController(CustomsOfficeService customsOfficeService) {
        this.customsOfficeService = customsOfficeService;
    }

    @GetMapping("/")
    public List<CustomsOffice> getAllCustomsOffices(){
        return this.customsOfficeService.getAllCustomsOffices();
    }

    @GetMapping("/{id}")
    public CustomsOffice getCustomsOfficeById(@PathVariable Long id){
        return this.customsOfficeService.getCustomsOfficeById(id);
    }
}
