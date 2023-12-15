package tr.gov.gib.taskman.dao.model;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import tr.gov.gib.taskman.dao.model.common.BaseModel;

@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE notes SET isdeleted = true WHERE id = ? and version = ?")

@EntityListeners(AuditingEntityListener.class)
public class Note extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Memo")
    private String memo;
}

