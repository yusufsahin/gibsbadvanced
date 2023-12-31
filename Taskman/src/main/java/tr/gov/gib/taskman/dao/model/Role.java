package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.gov.gib.taskman.dao.model.common.BaseModel;

import java.util.Collection;

@Entity
@Table(name = "Roles",uniqueConstraints = {
        @UniqueConstraint(name = "UniqueRoleName", columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE roles SET isdeleted = true WHERE id = ? and version = ?")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name="Name", nullable = false, unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(mappedBy="roles")
    private Collection<User> users;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_PRIVILEGES",
            joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
