package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment;

public class ITcertifications {
    private String certificate_id;
    private String skillname;
    private String expFrom;
    private String expTo;
    private String user_id;

    public ITcertifications(String skillname, String expFrom, String expTo, String user_id) {
        this.skillname = skillname;
        this.expFrom = expFrom;
        this.expTo = expTo;
        this.user_id = user_id;
    }
    public ITcertifications(){}

    public String getCertificate_id() {
        return certificate_id;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getExpFrom() {
        return expFrom;
    }

    public String getExpTo() {
        return expTo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setCertificate_id(String certificate_id) {
        this.certificate_id = certificate_id;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public void setExpFrom(String expFrom) {
        this.expFrom = expFrom;
    }

    public void setExpTo(String expTo) {
        this.expTo = expTo;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
