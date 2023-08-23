package projet.pfe.tms.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.pfe.tms.models.Agrement;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.repositories.AgrementRepo;
import projet.pfe.tms.services.AgrementService;

import java.util.List;

@Service
public class AgrementServiceImp implements AgrementService {

    @Autowired
    private AgrementRepo agrementRepository;

    @Override
    public List<Agrement> getAllAgrements() {
        return agrementRepository.findAll();
    }

    @Override
    public Agrement createAgrement(Agrement agrement) {
        return agrementRepository.save(agrement);
    }
    
    @Override
    public Agrement getAgrementById(Long id) {
    Agrement agrement = agrementRepository.findById(id).orElse(null);
    if (agrement == null) {
        return null;
    }
    return agrement;
}


}
