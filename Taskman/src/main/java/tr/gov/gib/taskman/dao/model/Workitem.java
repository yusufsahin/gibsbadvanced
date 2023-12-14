package tr.gov.gib.taskman.dao.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("projectId")
    private Project project;

}
