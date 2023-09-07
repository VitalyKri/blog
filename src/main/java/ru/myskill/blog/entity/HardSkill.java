package ru.myskill.blog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.myskill.blog.entity.common.InfoEntity;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hard_skills")
public class HardSkill extends InfoEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private boolean isDeleted;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "hardSkill")
    private Set<HardSkillUser> hardSkillsUsers;


}
