package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts", indexes = {
        @Index(name = "firstname_idx", columnList = "firstname"),
        @Index(name = "lastname_idx", columnList = "lastname"),
        @Index(name = "primary_idx", columnList = "is_primary")
})
public class ContactClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactid")
    private Long contactId;

    @Column(name = "is_primary", nullable = false)
    private boolean primary;

    @Column(name = "firstname", nullable = false, length = 300)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 300)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "phonenumber", nullable = false, length = 100)
    private String phoneNumber;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "datecreated", nullable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "new_pass_key", length = 32)
    private String newPassKey;

    @Column(name = "new_pass_key_requested")
    private LocalDateTime newPassKeyRequested;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "email_verification_key", length = 32)
    private String emailVerificationKey;

    @Column(name = "email_verification_sent_at")
    private LocalDateTime emailVerificationSentAt;

    @Column(name = "last_ip", length = 40)
    private String lastIp;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "last_password_change")
    private LocalDateTime lastPasswordChange;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "profile_image", length = 300)
    private String profileImage;

    @Column(name = "direction", length = 50)
    private String direction;

    @Column(name = "invoice_emails", nullable = false)
    private Boolean invoiceEmails = false;

    @Column(name = "estimate_emails", nullable = false)
    private Boolean estimateEmails = false;

    @Column(name = "credit_note_emails", nullable = false)
    private Boolean creditNoteEmails = false;

    @Column(name = "contract_emails", nullable = false)
    private Boolean contractEmails = false;

    @Column(name = "task_emails", nullable = false)
    private Boolean taskEmails = false;

    @Column(name = "project_emails", nullable = false)
    private Boolean projectEmails = false;

    @Column(name = "ticket_emails", nullable = false)
    private Boolean ticketEmails = false;

    @ManyToOne(optional = false)
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "client_id", referencedColumnName = "userid")
    private Client client;

}
