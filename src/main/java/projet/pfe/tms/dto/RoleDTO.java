package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private List<RolePermissionDTO> rolePermissions;
    public List<RolePermissionDTO> getRolePermissions() {
        return rolePermissions;
    }
}