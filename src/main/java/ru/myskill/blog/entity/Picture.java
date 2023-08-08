package ru.myskill.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.myskill.blog.entity.common.InfoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */
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
