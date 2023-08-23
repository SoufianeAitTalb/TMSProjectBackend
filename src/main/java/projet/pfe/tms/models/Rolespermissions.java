package projet.pfe.tms.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rolespermissions")
@NoArgsConstructor
@AllArgsConstructor
public class Rolespermissions {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolepermissionid")
    private int rolePermissionId;

    @Column(name = "can_view")
    private boolean canView;

    @Column(name = "can_edit")
    private boolean canEdit;
 
    @Column(name = "can_create")
    private boolean canCreate;
 
    @Column(name = "can_delete")
    private boolean canDelete;


    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private Role role;

    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "permissionid", referencedColumnName = "permissionid")
    private Permissions permission;
    
}
