package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class skills {
   private String skillid;
   private String skillname;
   private String skillLevel;
   private String user_id;
public skills(){}
    public skills( String skillname, String skillLevel,String user_id) {
        this.skillname = skillname;
        this.skillLevel=skillLevel;
        this.user_id = user_id;
    }

    public String getSkillid() {
        return skillid;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
