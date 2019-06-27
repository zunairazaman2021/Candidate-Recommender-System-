package com.example.zunairazamanchaudh.candidateengine.DatabaseRecruitment.ResumeDatabase;

public class Strengths {
private String strength_id;
private String strength_name;
private String cv_id;
public Strengths(){}

    public Strengths(String strength_name) {
        this.strength_name = strength_name;
    }

    public String getStrength_id() {
        return strength_id;
    }

    public String getStrength_name() {
        return strength_name;
    }

    public void setStrength_id(String strength_id) {
        this.strength_id = strength_id;
    }

    public void setStrength_name(String strength_name) {
        this.strength_name = strength_name;
    }

    public String getCv_id() {
        return cv_id;
    }

    public void setCv_id(String cv_id) {
        this.cv_id = cv_id;
    }
}
