package projet.pfe.tms.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientSupplierDTO {
	
	private Long id;
		
	private Long supplierId;
	
	private Long clientId;
	
}
