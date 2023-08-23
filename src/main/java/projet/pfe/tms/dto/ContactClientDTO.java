package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactClientDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String direction;
    private boolean primary;
    private boolean active;
    private String profileImage;
    private String title;

    private Long clientId;
    private String company;

}
