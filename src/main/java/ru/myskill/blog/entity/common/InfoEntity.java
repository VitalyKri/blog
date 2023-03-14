package ru.myskill.blog.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class InfoEntity extends BaseEntity {

    @Version
    @Column(name = "VERSION")
    private int version;
//    @CreatedBy
//    @Column(name = "CREATED_BY", updatable = false)
//    private String createdBy;
//    @CreatedDate
//    @Column(name = "CREATED_DATE", updatable = false)
//    private LocalDateTime createdDate;
//    @LastModifiedBy
//    @Column(name = "LAST_MODIFIED_BY")
//    private String lastModifiedBy;
//    @LastModifiedDate
//    @Column(name = "LAST_MODIFIED_DATE")
//    private LocalDateTime lastModifiedDate;

    public InfoEntity(UUID id, int version) {
        super(id);
        this.version = version;
//        this.createdBy = createdBy;
//        this.createdDate = createdDate;
//        this.lastModifiedBy = lastModifiedBy;
//        this.lastModifiedDate = lastModifiedDate;
    }
}
