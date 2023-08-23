package projet.pfe.tms.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "staff", indexes = {
	    @Index(name = "firstname_idx", columnList = "firstname"),
	    @Index(name = "lastname_idx", columnList = "lastname")
	})
public class Staff {
		
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private Long staffId;
    
    @Column(name = "firstname", nullable = false)
    private String firstName;
    
    @Column(name = "lastname", nullable = false)
    private String lastName;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "facebook", columnDefinition = "mediumtext")
    private String facebook;
    
    @Column(name = "linkedin", columnDefinition = "mediumtext")
    private String linkedin;
    
    @Column(name = "phonenumber")
    private String phoneNumber;
    
    @Column(name = "skype")
    private String skype;
    
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated = LocalDateTime.now();
    
    @Column(name = "profile_image", length = 300)
    private String profileImage;
    
    @Column(name = "last_ip", length = 40)
    private String lastIp;

    @Column(name = "last_login")
    private LocalDateTime lastLogin = LocalDateTime.now();
    
    @Column(name = "last_activity")
    private Date lastActivity;
    
    @Column(name = "last_password_change")
    private Date lastPasswordChange;
    
    @Column(name = "new_pass_key", length = 32)
    private String newPassKey;
    
    @Column(name = "new_pass_key_requested")
    private Date newPassKeyRequested;
    
    @Column(name = "admin")
	@Value("${statut:false}")
    private boolean admin;
    
    @Column(name = "active")
    private boolean active = true;
    
    @Column(name = "default_language", length = 40)
    private String defaultLanguage;
    
    @Column(name = "direction", length = 3)
    private String direction;
    
    @Column(name = "media_path_slug", length = 300)
    private String mediaPathSlug;
    
    @Column(name = "is_not_staff")
	@Value("${statut:false}")
    private boolean isNotStaff;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private double hourlyRate = 0.00;
    
    @Column(name = "two_factor_auth_enabled")
	@Value("${statut:false}")
    private boolean twoFactorAuthEnabled;
    
    @Column(name = "two_factor_auth_code", length = 100)
    private String twoFactorAuthCode;
    
    @Column(name = "two_factor_auth_code_requested")
    private Date twoFactorAuthCodeRequested;
    
    @Column(name = "email_signature")
    private String emailSignature;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIgnore
    @JsonBackReference
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private Role role;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Client> clients;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Agent> agents;

	public boolean isAllowed() {
		return !this.isNotStaff;
	}
	
	public boolean isActived() {
        return this.isActive();
	}
	
	@Override
	public String toString() {
	    String roleName = role != null ? role.getRoleName() : "N/A";
	    return "Staff{" +
	            "staffId=" + staffId +
	            ", firstName='" + firstName + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", email='" + email + '\'' +
	            ", role=" + roleName +
	            '}';
	}
}
