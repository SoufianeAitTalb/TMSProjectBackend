
package projet.pfe.tms.services;

import projet.pfe.tms.dto.NgpDTO;
import projet.pfe.tms.models.Ngp;

import java.util.List;

public interface NgpService {

    Ngp createNgp(NgpDTO ngpDTO);

    Ngp getNgpById(Long id);
    List<Ngp> listNgp();

    Ngp updateNgp(Long id, NgpDTO ngpDTO );

    void deleteNgp(Long id);

    Ngp loadNgpBycodeProduct(String codeProduct);

}
