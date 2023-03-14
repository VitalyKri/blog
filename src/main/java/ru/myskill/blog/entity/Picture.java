package ru.myskill.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.myskill.blog.entity.common.InfoEntity;


/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "pictures")
public class Picture extends InfoEntity {
    @Column
    private String pathPicture;

    @Column
    private String description;
}
