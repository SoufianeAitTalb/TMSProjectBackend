package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.models.Client;
import projet.pfe.tms.models.Supplier;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NgpDTO {
    private String codeProduct;
    private String desi;
    private String Ngp;
    private String dossier;
    private long supplierId ;
    private long clientId;
    private String origine;
    private Date date_created;
}
