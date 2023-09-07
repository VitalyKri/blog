package ru.myskill.blog.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hard_skills_users")
public class HardSkillUser {

    @Version
    @Column(name = "VERSION")
    private int version;

    @Embeddable
    public class SkillUserCompositeKey implements Serializable {


        @Column(name = "user_id")
        String userId;
        @Column(name = "hard_skill_id")
        String hardSkillId;

        public SkillUserCompositeKey() {
        }

        public SkillUserCompositeKey(String userId, String hardSkillId) {
            this.userId = userId;
            this.hardSkillId = hardSkillId;
        }

    }

    @EmbeddedId
    private SkillUserCompositeKey skillUserCompositeKey;

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

