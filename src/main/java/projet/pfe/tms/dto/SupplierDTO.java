package projet.pfe.tms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.pfe.tms.models.Country;
import projet.pfe.tms.models.Currency;
import projet.pfe.tms.models.SupplierContact;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String company;
    private String phoneNumber;
    private Long countryId;
    private String city;
    private String zip;
    private String state;
    private String address;
    private String website;
    private Long defaultCurrencyId;
    private String codeAuxi;
 //   private List<SupplierContact> supplierContactList;
}
