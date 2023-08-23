package projet.pfe.tms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.pfe.tms.dto.NgpDTO;
import projet.pfe.tms.models.Ngp;
import projet.pfe.tms.models.Supplier;
import projet.pfe.tms.repositories.NgpRepos;
import projet.pfe.tms.services.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@Transactional
public class NgpServiceImp implements NgpService {

    private final  NgpRepos ngpRepo;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ClientService clientService;


     @Autowired
    public NgpServiceImp(NgpRepos ngpRepo) {

         this.ngpRepo = ngpRepo;
    }


    @Override
    public Ngp createNgp(NgpDTO ngpDTO) {
         Ngp ngp = new  Ngp();
         ngp.setSupplier(this.supplierService.loadById(ngpDTO.getSupplierId()));
         ngp.setDesi(ngpDTO.getDesi());
         ngp.setDossier(ngpDTO.getDossier());
         ngp.setNgp(ngpDTO.getNgp());
         ngp.setCodeProduct(ngpDTO.getCodeProduct());
         ngp.setClient(this.clientService.loadClientById(ngpDTO.getClientId()));
         ngp.setOrigine(ngpDTO.getOrigine());
         ngp.setDate_created(ngpDTO.getDate_created());

        return ngpRepo.save(ngp);
    }

    @Override
    public Ngp getNgpById(Long id) {
        return this.ngpRepo
                .findById(id)
                .orElse(null);
    }
    @Override
    public List<Ngp> listNgp() {
        return this.ngpRepo.findAll();
    }

    @Override
    public Ngp updateNgp(Long id, NgpDTO ngpDTO) {
         Ngp ngp = ngpRepo.findByNgpId(id);
        ngp.setSupplier(this.supplierService.loadById(ngpDTO.getSupplierId()));
        ngp.setDesi(ngpDTO.getDesi());
        ngp.setDossier(ngpDTO.getDossier());
        ngp.setNgp(ngpDTO.getNgp());
        ngp.setCodeProduct(ngpDTO.getCodeProduct());
        ngp.setClient(this.clientService.loadClientById(ngpDTO.getClientId()));
        ngp.setOrigine(ngpDTO.getOrigine());
        ngp.setDate_created(ngpDTO.getDate_created());

        return ngpRepo.save(ngp);
    }

    @Transactional
    @Override
    public void deleteNgp(Long id) {
        ngpRepo.deleteById(id);
    }

    @Override
    public Ngp loadNgpBycodeProduct(String codeProduct) {

         return null;
    }
}
