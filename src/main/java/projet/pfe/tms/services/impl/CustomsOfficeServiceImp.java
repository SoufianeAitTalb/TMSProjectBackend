package projet.pfe.tms.services.impl;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.CustomsOffice;
import projet.pfe.tms.repositories.CustomsOfficeRepo;
import projet.pfe.tms.services.CustomsOfficeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomsOfficeServiceImp implements CustomsOfficeService {

    private final CustomsOfficeRepo customsOfficeRepo;

    @Autowired
    public CustomsOfficeServiceImp(CustomsOfficeRepo customsOfficeRepo) {
        this.customsOfficeRepo = customsOfficeRepo;
    }

    @Override
    public List<CustomsOffice> getAllCustomsOffices() {
        return this.customsOfficeRepo.findAll();
    }

    @Override
    public CustomsOffice getCustomsOfficeById(Long id) {
        return this.customsOfficeRepo
                .findById(id)
                .orElse(null);
    }

}
