package ru.myskill.blog.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.myskill.blog.entity.common.InfoEntity;
import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<HardSkillUser> hardSkillsUsers;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Subscriber> subscribers;


}
