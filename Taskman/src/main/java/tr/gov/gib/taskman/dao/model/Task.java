package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tr.gov.gib.taskman.dao.model.common.BaseModel;
import tr.gov.gib.taskman.enumerate.TaskStatus;
import tr.gov.gib.taskman.enumerate.converter.TaskStatusConverter;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE tasks SET isdeleted=true WHERE id=? and version=?")
@EntityListeners(AuditingEntityListener.class)
public class Task extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Convert(converter = TaskStatusConverter.class)
    @Column(name = "Status")
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workitemid",nullable = false)
    private Workitem workitem;
}
