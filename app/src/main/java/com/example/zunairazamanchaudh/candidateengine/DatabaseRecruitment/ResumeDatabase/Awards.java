package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Awards {
    private String award_id;
    private String award_name;
    private String cv_id;
    public Awards(){}

    public Awards(String award_name,String cv_id) {
        this.cv_id=cv_id;
        this.award_name = award_name;
    }

    public String getAward_id() {
        return award_id;
    }

    public String getAward_name() {
        return award_name;
    }

    public void setAward_id(String award_id) {
        this.award_id = award_id;
    }

    public void setAward_name(String award_name) {
        this.award_name = award_name;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }
}
