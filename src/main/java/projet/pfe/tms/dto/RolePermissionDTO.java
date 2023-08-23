package projet.pfe.tms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDTO {
    private Long permissionId;
    private boolean canView;
    private boolean canCreate;
    private boolean canEdit;
    private boolean canDelete;
}