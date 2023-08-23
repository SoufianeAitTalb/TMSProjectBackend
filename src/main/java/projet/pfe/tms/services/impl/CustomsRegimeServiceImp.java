package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.CustomsRegime;
import projet.pfe.tms.repositories.CustomsRegimeRepo;
import projet.pfe.tms.services.CustomsRegimeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomsRegimeServiceImp implements CustomsRegimeService {

    private final CustomsRegimeRepo customsRegimeRepo;

    @Autowired
    public CustomsRegimeServiceImp(CustomsRegimeRepo customsRegimeRepo) {
        this.customsRegimeRepo = customsRegimeRepo;
    }

    @Override
    public List<CustomsRegime> getAllCustomsRegimes() {
        return this.customsRegimeRepo.findAll();
    }

    @Override
    public CustomsRegime getCustomsRegimeById(Long id) {
        return this.customsRegimeRepo
                .findById(id)
                .orElse(null);
    }
}
