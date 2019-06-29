package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class SkillsCV {
    private String cv_id;
    private String skill_id;
    private String skillName;
    public SkillsCV(){}

    public SkillsCV(String skillName,String cv_id) {
        this.cv_id=cv_id;
        this.skillName = skillName;
    }

    public String getSkill_id() {
        return skill_id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkill_id(String skill_id) {
        this.skill_id = skill_id;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }
}
