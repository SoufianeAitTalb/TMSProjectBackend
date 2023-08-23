package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customs_regime")
public class CustomsRegime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customs_regime_id")
    private Long customsRegimeId;

    @Column(name = "customs_regime_name", nullable = false, length = 256)
    private String customsRegimeName;

    @OneToMany(mappedBy = "customsRegime", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})

    private Set<Folder> customsRegimeFolders;

}
