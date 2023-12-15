package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.gov.gib.taskman.dao.model.common.BaseModel;

import java.util.Collection;
@Entity
@Table(name = "Privileges",uniqueConstraints = {
        @UniqueConstraint(name = "UniquePrivilegeName", columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE privileges SET isdeleted = true WHERE id = ? and version = ?")
@EntityListeners(AuditingEntityListener.class)
public class Privilege extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name",unique = true)
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(mappedBy="privileges")
    private Collection<Role> roles;

}
