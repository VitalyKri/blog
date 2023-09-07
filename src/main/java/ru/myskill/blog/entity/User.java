package ru.myskill.blog.entity;


import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.myskill.blog.entity.common.InfoEntity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


/**
 * @author Vitaly Krivobokov
 * @Date 13.03.2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@SuperBuilder
public class User extends InfoEntity {


    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String gender;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String city;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @Column
    private String aboutMe;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private boolean isDeleted;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "user")
    private Set<HardSkillUser> hardSkillsUsers;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Subscriber> subscribers;


}
