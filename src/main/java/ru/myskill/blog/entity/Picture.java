package ru.myskill.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.myskill.blog.entity.common.InfoEntity;
import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pictures")
public class Picture extends InfoEntity {
    @Column
    private String pathPicture;

    @Column
    private String description;
}
