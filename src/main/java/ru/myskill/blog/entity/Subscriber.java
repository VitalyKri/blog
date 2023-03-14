package ru.myskill.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.myskill.blog.entity.common.InfoEntity;



/**
 * @author Vitaly Krivobokov
 * @Date 14.03.2023
 */

@Getter
@Setter

@AllArgsConstructor
@Entity(name = "subscribers")
public class Subscriber extends InfoEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @Column
    private Boolean isDeleted;

}
