package tr.gov.gib.taskman.dao.model.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @CreatedBy
    @Column(name = "CreatedBy")
    private  String createdBy;

    @CreatedDate
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedBy
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    @Column(name = "IsDeleted")
    private Boolean isDeleted=false;

    @Version
    @Column(name = "Version")
    private int version;
}
