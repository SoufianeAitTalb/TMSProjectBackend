package projet.pfe.tms.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role  {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
	private Long roleId;
	
	@Column(name = "name", length = 150)
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	@JsonIgnore
	private List<Staff> staffs;

	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("role")
	private List<Rolespermissions> rolePermissions;
}
