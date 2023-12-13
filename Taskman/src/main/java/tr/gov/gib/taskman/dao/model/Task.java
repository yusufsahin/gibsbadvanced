package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.gov.gib.taskman.dao.model.common.BaseModel;
import tr.gov.gib.taskman.enumerate.TaskStatus;
import tr.gov.gib.taskman.enumerate.converter.TaskStatusConverter;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
