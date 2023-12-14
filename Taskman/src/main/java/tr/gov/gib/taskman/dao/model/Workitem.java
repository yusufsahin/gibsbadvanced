package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import tr.gov.gib.taskman.dao.model.common.BaseModel;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Workitems")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql="UPDATE workitems SET isdeleted=true WHERE id=? and version=?")
public class Workitem extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id",nullable=false)
    private Long id;

    @Column(name="Name")
    private String name;
    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "workitem",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Task> tasks= new HashSet<>();
}
