package projet.pfe.tms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipients")
public class Recipient {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "recipient_id")
    private Long recipientId;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "address",length = 255,nullable = false)
    private String address;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})

    private Set<Folder> recipientFolders= new HashSet<Folder>();
}