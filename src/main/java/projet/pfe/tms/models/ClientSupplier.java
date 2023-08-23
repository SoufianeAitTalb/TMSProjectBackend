package projet.pfe.tms.models;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="client_suppliers")
public class ClientSupplier {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)

	    @JoinColumn(name = "supplier_id")
		@JsonIgnore
	    private Supplier supplier;

	    @ManyToOne(fetch = FetchType.LAZY)

	    @JoinColumn(name = "client_id")
		@JsonIgnore
	    private Client client;
	

}
