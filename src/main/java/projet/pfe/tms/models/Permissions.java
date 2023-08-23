package projet.pfe.tms.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
public class Permissions {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionid")
	private Long idPermission;

    @Column(name = "name", columnDefinition = "mediumtext")
    private String name;

    @Column(name = "shortname", columnDefinition = "mediumtext")
    private String shortName;

    @OneToMany(mappedBy = "permission")
    @JsonIgnore
    private List<Rolespermissions> permissionsRole;
}
