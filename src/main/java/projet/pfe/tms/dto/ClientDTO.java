package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String name;
    private String company;
    private String iceClient;
    private String phoneNumber;
    private String email;
    private double enCours;
    private int echeance;
    private String website;
    private String rc;
    private String cnss;

    private String address;
    private String city;
    private String zip;
    private Long countryId;
    private String countryLongName;


    private String codeComptable;
    private String codeAuxi;
    private Long defaultCurrencyId;
    private Double patente;

    private String billingStreet;
    private String billingState;
    private String billingCity;
    private String billingZip;
    private Long billingCountryId;

    private String shippingStreet;
    private String shippingState;
    private String shippingCity;
    private String shippingZip;
    private Long shippingCountryId;

    private LocalDateTime dateAffectation;
    private String staffFullName;
    private Long staffId;
}
