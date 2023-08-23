package projet.pfe.tms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private Long agentId;
    private String name;
    private String company;
    private String phoneNumber;
    private String email;
    private boolean active;
    private String website;

    private String cnss;

    private String address;
    private String city;
    private String zip;
    private Long countryId;
    private String countryLongName;


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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dateAffectation;
    private String staffFullName;
    private Long staffId;

}
