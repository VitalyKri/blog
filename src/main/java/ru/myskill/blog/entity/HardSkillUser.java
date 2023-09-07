package ru.myskill.blog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author Vitaly Krivobokov
 * @Date 13.03.2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hard_skills_users")
public class HardSkillUser  {

    @Version
    @Column(name = "VERSION")
    private int version;

    @Embeddable
    public class HSUCompositeKey implements Serializable {


        String user_id;
        String hard_skill_id;

        public HSUCompositeKey() {
        }
        public HSUCompositeKey(String user_id, String hard_skill_id) {
            this.user_id = user_id;
            this.hard_skill_id = hard_skill_id;
        }

    }

    @EmbeddedId
    private HSUCompositeKey HSUCompositeKey;

    @ManyToOne
    @MapsId("user_id")
    private User user;

    @ManyToOne
    @MapsId("hard_skill_id")
    private HardSkill hardSkill;

    @Column
    private String description;

    @Column
    private int rating;




}

